package estate.management.com.payload.response.user.concretes;

import estate.management.com.payload.response.user.abstracts.BaseUserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserResponseWithRoleWithoutPassword extends BaseUserResponse {
    private String role;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
