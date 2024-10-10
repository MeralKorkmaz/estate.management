package estate.management.com.payload.request.user.concretes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Column(name = "email", nullable = false, unique = true, length = 80)
    @Size(min = 10, max = 80, message = "email must be between   {min} and {max} characters")
    @NotNull(message = "email cannot be null")
    @Email
    private String email;

    //TODO password validation ekle
    @Column(name = "password_hash", nullable = false)
    @NotNull(message = "password cannot be null")
    private String password;
}
