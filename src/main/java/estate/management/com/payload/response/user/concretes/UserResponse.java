package estate.management.com.payload.response.user.concretes;

import com.fasterxml.jackson.annotation.JsonInclude;
import estate.management.com.payload.response.user.abstracts.BaseUserResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
public class UserResponse extends BaseUserResponse {
    private String role;
}
