package estate.management.com.payload.request.concrete;


import estate.management.com.payload.request.abstracts.BaseUserRequest;


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
