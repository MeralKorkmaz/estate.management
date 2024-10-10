package estate.management.com.service;


import estate.management.com.domain.Favorite;
import estate.management.com.domain.user.RoleType;
import estate.management.com.domain.user.User;
import estate.management.com.exception.BadRequestException;
import estate.management.com.exception.ResourceNotFoundException;

import estate.management.com.mail.helpermethod.GenerateResetCode;
import estate.management.com.mail.mailpayload.MailPayload;
import estate.management.com.mail.service.impl.EmailServiceImpl;
import estate.management.com.payload.mapper.user.UserMapper;
import estate.management.com.payload.message.ErrorMessages;
import estate.management.com.payload.message.SuccessMessages;

import estate.management.com.payload.request.user.concretes.*;

import estate.management.com.payload.response.ResponseMessage;


import estate.management.com.payload.response.user.abstracts.BaseUserResponse;
import estate.management.com.payload.response.user.concretes.LoginResponse;
import estate.management.com.payload.response.user.concretes.UserResponse;
import estate.management.com.payload.response.user.concretes.UserResponseWithRoleWithoutPassword;
import estate.management.com.repository.UserRepository;
import estate.management.com.security.jwt.JwtUtils;
import estate.management.com.security.service.UserDetailsImpl;


import estate.management.com.service.business.AdvertService;
import estate.management.com.service.business.FavoriteService;
import estate.management.com.service.business.TourRequestService;
import estate.management.com.service.user.validator.ValidatorForUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${backendapi.app.mailaddress}")
    private String mailaddress;

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ValidatorForUser validatorForUser;
    private final estate.management.com.service.user.UserRoleService userRoleService;
    private final EmailServiceImpl emailService;
    private final GenerateResetCode resetCode;
    private final AdvertService advertService;
    private final FavoriteService favoriteService;





    public ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = "Bearer " + jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Set<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        Optional<String> role = roles.stream().findFirst();

        LoginResponse.LoginResponseBuilder loginResponseBuilder = LoginResponse.builder();
        loginResponseBuilder.token(token.substring(7));
        loginResponseBuilder.email(userDetails.getEmail());
        loginResponseBuilder.firstName(userDetails.getFirstname());
        role.ifPresent(loginResponseBuilder::role);


        return ResponseEntity.ok(loginResponseBuilder.build());

    }

    public UserResponse findByEmail(String userEmail) {
        User user = userRepository.findByEmailEquals(userEmail);
        if (user.getId() == null) {
            throw new ResourceNotFoundException(ErrorMessages.NOT_FOUND_USER_MESSAGE);
        }
        return userMapper.mapUserToUserResponse(user);
    }

    public ResponseEntity<String> updatePassword(UpdatePasswordRequest updatePasswordRequest, HttpServletRequest request) {

        validatorForUser.confirmPasswordCheck(updatePasswordRequest.getNewPassword(), updatePasswordRequest.getConfirmPassword());
        String email = (String) request.getAttribute("email");
        User user = userRepository.findByEmailEquals(email);


        if (Boolean.TRUE.equals(user.getBuilt_in())) {
            throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
        }
        if (!passwordEncoder.matches(updatePasswordRequest.getOldPassword(), user.getPasswordHash())) {
            throw new BadRequestException(ErrorMessages.PASSWORD_SHOULD_NOT_MATCHED);
        }
        String hashedPassword = passwordEncoder.encode(updatePasswordRequest.getNewPassword());
        user.setPasswordHash(hashedPassword);
        userRepository.save(user);
        return new ResponseEntity<>(SuccessMessages.PASSWORD_CHANGED_RESPONSE_MESSAGE, HttpStatus.OK);
    }

    public ResponseMessage<UserResponse> saveUser(UserRequest userRequest) {

        validatorForUser.confirmPasswordCheck(userRequest.getPasswordHash(), userRequest.getConfirmPassword());
        validatorForUser.validateForPassword(userRequest.getPasswordHash());
        validatorForUser.uniqueEmail(userRequest.getEmail());

        User user = userMapper.mapUserRequestToUser(userRequest);
        user.setBuilt_in(false);
        user.setPasswordHash(passwordEncoder.encode(userRequest.getPasswordHash()));
        user.setUserRole(userRoleService.getUserRole(RoleType.CUSTOMER));
        user.setResetPasswordCode(passwordEncoder.encode(userRequest.getPasswordHash()));
        User savedUser = userRepository.save(user);
        UserResponse userResponse = userMapper.mapUserToUserResponse(savedUser);
        return new ResponseMessage<>(userResponse, SuccessMessages.CUSTOMER_REGISTER_SUCCESS, HttpStatus.CREATED);
    }

    //bu methodu BUG  cozmek icin yazdim sonra silecem saveUser methodu ile ilgili birlestirme yap saverUser methodunu cagir rolu setle
    public ResponseMessage<UserResponse> saveAdmin(UserRequest userRequest) {

        validatorForUser.confirmPasswordCheck(userRequest.getPasswordHash(), userRequest.getConfirmPassword());
        validatorForUser.validateForPassword(userRequest.getPasswordHash());
        validatorForUser.uniqueEmail(userRequest.getEmail());

        User user = userMapper.mapUserRequestToUser(userRequest);
        user.setBuilt_in(true);
        user.setPasswordHash(passwordEncoder.encode(userRequest.getPasswordHash()));
        user.setUserRole(userRoleService.getUserRole(RoleType.ADMIN));
        user.setResetPasswordCode(passwordEncoder.encode(userRequest.getPasswordHash()));
        User savedUser = userRepository.save(user);
        UserResponse userResponse = userMapper.mapUserToUserResponse(savedUser);
        return new ResponseMessage<>(userResponse, SuccessMessages.CUSTOMER_REGISTER_SUCCESS, HttpStatus.CREATED);
    }

    public long countAllAdmins() {
        return userRepository.countAdmin(RoleType.ADMIN);
    }

    public String deleteUserById(Long id, HttpServletRequest httpServletRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> //silinecek user
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE, id)));

        String userEmailWantsToDelete = (String) httpServletRequest.getAttribute("email");
        User userWantsToDelete = userRepository.findByEmailEquals(userEmailWantsToDelete);
        if (Boolean.TRUE.equals(user.getBuilt_in())) {
            throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);

        } else if (userWantsToDelete.getUserRole().getRoleType() == RoleType.ADMIN) {
            if ((user.getUserRole().getRoleType() == RoleType.ADMIN) && (user.getId() != userWantsToDelete.getId())) {
                throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
            }
        } else if (userWantsToDelete.getUserRole().getRoleType() == RoleType.MANAGER) {
            if (user.getUserRole().getRoleType() == RoleType.MANAGER || user.getUserRole().getRoleType() == RoleType.ADMIN) {
                throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
            } else if (userWantsToDelete.getUserRole().getRoleType() == RoleType.CUSTOMER) {
                if (user.getUserRole().getRoleType() == RoleType.MANAGER || user.getUserRole().getRoleType() == RoleType.ADMIN || (user.getUserRole().getRoleType() == RoleType.CUSTOMER && user.getId() != userWantsToDelete.getId())) {
                    throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
                }
            }
        }

        //TODO buraya favori ve log entitlrinin delete methodu cagirilacak
        userRepository.deleteById(id);
        favoriteService.deleteAllFavoritesByAdminAndManager(Math.toIntExact(user.getId()));

        return SuccessMessages.USER_DELETE_BY_ADMIN_OR_MANAGER;
    }


    public ResponseMessage<BaseUserResponse> updateUserByAdmin(UserRequestForUpdatingByAdmin userRequestForUpdatingByAdmin, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE, userId)));

        validatorForUser.checkBuiltin(user);
        validatorForUser.uniqueEmail(user.getEmail());

        User updatedUser = userMapper.mapUserRequestForUpdatingByAdminToUser(userRequestForUpdatingByAdmin);
        updatedUser.setId(userId);

        User savedUser = userRepository.save(updatedUser);

        return ResponseMessage.<BaseUserResponse>builder()
                .message(SuccessMessages.USER_UPDATE_MESSAGE)
                .status(HttpStatus.OK)
                .object(userMapper.mapUserToUserResponse(savedUser))
                .build();

    }

    public ResponseEntity<UserResponse> updateUserByThemselves(UserRequestWithoutPassword userRequestWithoutPassword, HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        User user = userRepository.findByEmailEquals(email);
        validatorForUser.checkBuiltin(user);

        if (!(userRequestWithoutPassword.getEmail().equals(email))) {
            validatorForUser.uniqueEmail(userRequestWithoutPassword.getEmail());
            user.setEmail(userRequestWithoutPassword.getEmail());
        }
        user.setFirstName(userRequestWithoutPassword.getFirstName());
        user.setLastName(userRequestWithoutPassword.getLastName());
        user.setPhone(userRequestWithoutPassword.getPhone());
        userRepository.save(user);
        return ResponseEntity.ok(userMapper.mapUserToUserResponse(user));

    }


    public Page<User> searchUsers(String query, int page, int size, String sortField, String sortType) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortType), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.search(query, pageable);
    }

    public ResponseMessage<UserResponseWithRoleWithoutPassword> getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow((() ->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE, userId))));

        return ResponseMessage.<UserResponseWithRoleWithoutPassword>builder()
                .message(SuccessMessages.USER_FOUND)
                .status(HttpStatus.OK)
                .object(userMapper.mapUserToUserResponseWithRoleWithoutPassword(user))
                .build();
    }


    public ResponseEntity<String> deleteAuthUser(UserDeleteRequest userDeleteRequest, HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        User user = userRepository.findByEmailEquals(email);
        if (Boolean.TRUE.equals(user.getBuilt_in())) {
            throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
        }
        if (!passwordEncoder.matches(userDeleteRequest.getPassword(), user.getPasswordHash())) {
            throw new BadRequestException(ErrorMessages.WRONG_PASSWORD_MESSAGE);
        }

        userRepository.deleteById(user.getId());


        return ResponseEntity.ok(SuccessMessages.USER_DELETE_BY_CUSTOMER);

    }

    public String forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        User user = userRepository.findByEmailEquals(forgotPasswordRequest.getEmail());
        String mail = forgotPasswordRequest.getEmail();
        if (user == null) {
            throw new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE, forgotPasswordRequest.getEmail()));
        }
        String code = resetCode.generateResetCode();
        MailPayload mailPayload= MailPayload.builder()
                .from(mailaddress)
                .to(mail)
                .subject("Luvenda Real Estate!!! Password Reset Code")
                .text("Luvenda Real Estate Your password reset code is: " + code)
                .build();
        emailService.sendEmail(mailPayload);

        return SuccessMessages.FORGOT_PASSWORD_RESET_CODE_EMAIL_SEND;
    }
}