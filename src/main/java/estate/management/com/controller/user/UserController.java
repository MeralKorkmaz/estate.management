package estate.management.com.controller.user;

import estate.management.com.domain.user.User;
import estate.management.com.payload.request.user.concretes.*;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.payload.response.user.abstracts.BaseUserResponse;
import estate.management.com.payload.response.user.concretes.LoginResponse;
import estate.management.com.payload.response.user.concretes.UserResponse;
import estate.management.com.payload.response.user.concretes.UserResponseWithRoleWithoutPassword;
import estate.management.com.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")      // F1 It will authenticate the user
    //   http://localhost:8080/users/login + post + Json = {"email": "admin_Cin_Ali@admin.com","password": "Ab123456*"}
    public ResponseEntity<LoginResponse> loginUser(@RequestBody @Valid LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);
    }


    //F2 It will create the user    //http://localhost:8080/users/register  + post + json = {"email": "At_hirsizi@gmail.com","firstName": "At","lastName": "Hirsiz","phone":"123456789","passwordHash": "Abc1234*","confirmPassword":"Abc1234*","built_in": false}
    @PostMapping("/register")
    public ResponseEntity<ResponseMessage<UserResponse>> saveUser(@RequestBody @Valid UserRequest userRequest) {

        return ResponseEntity.ok(userService.saveUser(userRequest));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody @Valid ForgotPasswordRequest forgotPasswordRequest) {
        return ResponseEntity.ok(userService.forgotPassword(forgotPasswordRequest));
    }

    // F5 It will return authenticated user object  // http://localhost:8080/users/users/auth + get
    @GetMapping("/users/auth")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','CUSTOMER')")
    public ResponseEntity<UserResponse> returnAuthUser(HttpServletRequest request) {
        String userEmail = (String) request.getAttribute("email");
        UserResponse userResponse = userService.findByEmail(userEmail);
        return ResponseEntity.ok(userResponse);
    }


    //F6 It will update the authenticated user -> kendi kendini update edecek cuxtomer manager admin

    @PutMapping("/user/auth")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','CUSTOMER')")
    public ResponseEntity<UserResponse> updateUserByThemselves(@RequestBody @Valid
                                                                   UserRequestWithoutPassword userRequestWithoutPassword,
                                                               HttpServletRequest request) {
        return userService.updateUserByThemselves(userRequestWithoutPassword, request);
    }


    @PatchMapping("/users/auth") // F7 It will update the authenticated user’s password
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','CUSTOMER')")
    public ResponseEntity<String> updatePassword(@RequestBody @Valid UpdatePasswordRequest updatePasswordRequest,
                                                 HttpServletRequest request) {
        return userService.updatePassword(updatePasswordRequest, request);

    }



    @DeleteMapping("/users/auth")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    //F8 It will delete authenticated user //TODO buraya silinecek favoriler ile ilgili method eklenecek
    public ResponseEntity<String> deleteAuthUser(@RequestBody UserDeleteRequest userDeleteRequest, HttpServletRequest request) {
        return userService.deleteAuthUser(userDeleteRequest, request);
    }


    @GetMapping("/users") //F9 It will return users //TODO postmandan gecmedi
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public Page<User> getUsers(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createAt") String sort,
            @RequestParam(defaultValue = "desc") String type) {
        return userService.searchUsers(q, page, size, sort, type);
    }


    @GetMapping("/users/{userId}") //F10 It will return a user    //buraya favorite tour logs bilgileri eklenecek
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseMessage<UserResponseWithRoleWithoutPassword> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }


    @PutMapping("/update/{userId}") // F11 It will update the user TODO postmandan gecmedi
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseMessage<BaseUserResponse> updateUserByAdmin(
            @RequestBody @Valid UserRequestForUpdatingByAdmin userRequestForUpdatingByAdmin,
            @PathVariable Long userId) {
        return userService.updateUserByAdmin(userRequestForUpdatingByAdmin, userId);
    }


    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")

    //F12  It will delete the user  //TODO buraya favori ve log entitlrinin delete methodu cagirilacak
    public ResponseEntity<String> deleteUserById(@PathVariable Long id,
                                                 HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(userService.deleteUserById(id, httpServletRequest));
    }
}
