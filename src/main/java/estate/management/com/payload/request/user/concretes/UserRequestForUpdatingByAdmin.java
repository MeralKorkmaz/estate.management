package estate.management.com.payload.request.user.concretes;

import estate.management.com.domain.user.UserRole;
import estate.management.com.payload.request.user.abstracts.AbstractUserRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestForUpdatingByAdmin extends AbstractUserRequest {
    private UserRole role;
}
