package estate.management.com.payload.request.user.concretes;

import estate.management.com.payload.request.user.abstracts.AbstractUserRequest;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor

public class UserRequestWithoutPassword extends AbstractUserRequest {
}
