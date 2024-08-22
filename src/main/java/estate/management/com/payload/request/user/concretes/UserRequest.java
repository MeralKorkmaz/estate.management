package estate.management.com.payload.request.user.concretes;

import estate.management.com.payload.request.user.abstracts.BaseUserRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest extends BaseUserRequest {

    private String confirmPassword;
}
