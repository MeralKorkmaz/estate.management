package estate.management.com.payload.mapper;

import estate.management.com.domain.user.User;

import estate.management.com.payload.request.user.concretes.UserRequest;
import estate.management.com.payload.request.user.concretes.UserRequestForUpdatingByAdmin;
import estate.management.com.payload.request.user.concretes.UserRequestWithoutPassword;
import estate.management.com.payload.response.user.concretes.UserResponse;
import estate.management.com.payload.response.user.concretes.UserResponseWithRoleWithoutPassword;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public UserResponse mapUserToUserResponse(User user) {
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getUserRole().getRoleType().name())
                .build();

        return userResponse;
    }

    public User mapUserRequestToUser(UserRequest userRequest) {
        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .build();
        return user;
    }

    public User UserRequestWithoutPassword(UserRequestWithoutPassword UserRequestWithoutPassword) {
        User user = User.builder()
                .firstName(UserRequestWithoutPassword.getFirstName())
                .lastName(UserRequestWithoutPassword.getLastName())
                .phone(UserRequestWithoutPassword.getPhone())
                .build();
        return user;
    }

    public UserResponseWithRoleWithoutPassword mapUserToUserResponseWithRoleWithoutPassword(User user) {
        UserResponseWithRoleWithoutPassword userResponse = UserResponseWithRoleWithoutPassword.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getUserRole().getRoleType().name())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .build();
        return userResponse;
    }

    public User mapUserRequestForUpdatingByAdminToUser(UserRequestForUpdatingByAdmin userRequestForUpdatingByAdmin) {
        User user = User.builder()
                .firstName(userRequestForUpdatingByAdmin.getFirstName())
                .lastName(userRequestForUpdatingByAdmin.getLastName())
                .email(userRequestForUpdatingByAdmin.getEmail())
                .phone(userRequestForUpdatingByAdmin.getPhone())
                .userRole(userRequestForUpdatingByAdmin.getRole())
                .build();
        return user;
    }
}
