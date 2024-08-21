//package estate.management.com.payload.mapper.user;
//
//
//import estate.management.com.domain.user.User;
//import estate.management.com.payload.request.UserRequest;
//import estate.management.com.payload.response.UserResponse;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserMapper {
//
//
//    public UserResponse mapUserToUserResponse(User user) {
//        UserResponse userResponse=   UserResponse.builder()
//                .id(user.getId())
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .email(user.getEmail())
//                .phone(user.getPhone())
//                .build();
//
//        return userResponse;
//    }
//    public User mapUserRequestToUser(UserRequest userRequest){
//        User user= User.builder()
//                .firstName(userRequest.getFirstName())
//                .lastName(userRequest.getLastName())
//                .email(userRequest.getEmail())
//                .phone(userRequest.getPhone())
//                .build();
//        return user;
//    }
//
//}
//
//public class UserMapper {
//}
