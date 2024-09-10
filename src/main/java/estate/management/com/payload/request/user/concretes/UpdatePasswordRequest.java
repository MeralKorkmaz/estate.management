package estate.management.com.payload.request.user.concretes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequest {

    @NotBlank(message = "please enter old password")
    private String oldPassword;

    @NotBlank(message = "please enter new password")

    private String newPassword;


    @NotBlank(message = "please enter new password")
    private String confirmPassword;
}
