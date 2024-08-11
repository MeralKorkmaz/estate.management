package estate.management.com.service;

import estate.management.com.domain.user.RoleType;
import estate.management.com.domain.user.User;
import estate.management.com.exception.BadRequestException;
import estate.management.com.payload.mappers.UserMapper;
import estate.management.com.payload.messages.ErrorMessages;
import estate.management.com.payload.messages.SuccessMessages;
import estate.management.com.payload.request.LoginRequest;
import estate.management.com.payload.request.UpdatePasswordRequest;
import estate.management.com.payload.request.UserRequest;
import estate.management.com.payload.response.LoginResponse;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.payload.response.UserResponse;
import estate.management.com.repository.UserRepository;
import estate.management.com.security.jwt.JwtUtils;
import estate.management.com.security.service.UserDetailsImpl;
import estate.management.com.service.validator.ValidatorForUser;
import lombok.RequiredArgsConstructor;
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

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ValidatorForUser validatorForUser;
    private final UserRoleService userRoleService;


    public ResponseEntity<LoginResponse> authenticateUser(LoginRequest loginRequest) {
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

    public UserResponse findByEmail(String useremail) {
        User user = userRepository.findByEmailEquals(useremail);
        return userMapper.mapUserToUserResponse(user);
    }

    public ResponseEntity<String> updatePassword(UpdatePasswordRequest updatePasswordRequest, HttpServletRequest request) {
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
        if (userRepository.existsByEmailEquals(userRequest.getEmail())) {
            throw new BadRequestException(ErrorMessages.ALREADY_REGISTER_MESSAGE_EMAIL);
        }
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
        if (userRepository.existsByEmailEquals(userRequest.getEmail())) {
            throw new BadRequestException(ErrorMessages.ALREADY_REGISTER_MESSAGE_EMAIL);
        }
        User user = userMapper.mapUserRequestToUser(userRequest);
        user.setBuilt_in(true);
        user.setPasswordHash(passwordEncoder.encode(userRequest.getPasswordHash()));
        user.setUserRole(userRoleService.getUserRole(RoleType.ADMIN));
        user.setResetPasswordCode(passwordEncoder.encode(userRequest.getPasswordHash()));
        User savedUser = userRepository.save(user);
        UserResponse userResponse = userMapper.mapUserToUserResponse(savedUser);
        return new ResponseMessage<>(userResponse, SuccessMessages.CUSTOMER_REGISTER_SUCCESS, HttpStatus.CREATED);
    }
    public long countAllAdmins(){
        return userRepository.countAdmin(RoleType.ADMIN);
    }
}
