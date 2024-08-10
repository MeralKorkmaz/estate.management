package estate.management.com.controller.user;

import estate.management.com.payload.messages.SuccessMessages;
import estate.management.com.payload.request.LoginRequest;
import estate.management.com.payload.request.UpdatePasswordRequest;
import estate.management.com.payload.request.UserRequest;
import estate.management.com.payload.response.LoginResponse;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.payload.response.UserResponse;
import estate.management.com.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/login") //   http://localhost:8080/users/login + post + Json = {"email": "admin_Cin_Ali@admin.com","password": "Ab123456*"}
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) {
        return userService.authenticateUser(loginRequest);
    }


    @PostMapping("/register") //http://localhost:8080/users/register  + post + json = {"email": "At_hirsizi@gmail.com","firstName": "At","lastName": "Hirsiz","phone":"123456789","passwordHash": "Abc1234*","confirmPassword":"Abc1234*","built_in": false}
    public ResponseEntity<ResponseMessage<UserResponse>> saveUser(@RequestBody @Valid UserRequest userRequest) {

        return ResponseEntity.ok(userService.saveUser(userRequest ));
    }


    @GetMapping("/users/auth") // http://localhost:8080/auth/user + get
    public ResponseEntity<UserResponse> findByEmail(HttpServletRequest request) {
        String useremail = (String) request.getAttribute("email");
        UserResponse userResponse = userService.findByEmail(useremail);
        return ResponseEntity.ok(userResponse);
    }

    @PatchMapping("users/updatePassword") // http://localhost:8080/auth/updatePassword +Patch + json

    public ResponseEntity<String> updatePassword(@RequestBody @Valid UpdatePasswordRequest updatePasswordRequest,
                                                 HttpServletRequest request) {
        return userService.updatePassword(updatePasswordRequest, request);

    }


}


