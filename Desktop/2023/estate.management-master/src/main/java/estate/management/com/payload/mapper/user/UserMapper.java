package estate.management.com.payload.mapper.user;

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
        UserResponseWithRoleWithoutPassword.UserResponseWithRoleWithoutPasswordBuilder userResponseBuilder = UserResponseWithRoleWithoutPassword.builder();

        if (user.getId() != null) {
            userResponseBuilder.id(user.getId());
        }

        if (user.getFirstName() != null) {
            userResponseBuilder.firstName(user.getFirstName());
        }

        if (user.getLastName() != null) {
            userResponseBuilder.lastName(user.getLastName());
        }

        if (user.getEmail() != null) {
            userResponseBuilder.email(user.getEmail());
        }

        if (user.getPhone() != null) {
            userResponseBuilder.phone(user.getPhone());
        }

        if (user.getUserRole() != null && user.getUserRole().getRoleType() != null) {
            userResponseBuilder.role(user.getUserRole().getRoleType().name());
        }

        if (user.getCreateAt() != null) {
            userResponseBuilder.createAt(user.getCreateAt());
        }

        if (user.getUpdateAt() != null) {
            userResponseBuilder.updateAt(user.getUpdateAt());
        }

        return userResponseBuilder.build();
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
