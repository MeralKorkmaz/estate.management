
package estate.management.com.contactmessage.dto;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ContactRequest {

    @NotNull(message = "Please enter your first name")
    @Size(min = 2,max = 30,message = "Your first name must be between {min} and {max} characters")
    private String firstName;

    @Nullable
    @Size(min = 2,max = 30,message = "Your last name must be between {min} and {max} characters")
    private String lastName;

    @Size(max = 60, message = "eMail cannot be more than {max} characters")
    @NotNull(message = "eMail cannot be null")
    @Email(message = "Please enter valid email")
    private String eMail;

    @Size(max = 300, message = "message cannot be more than {max} characters")
    @NotNull(message = "message cannot be null")
    private String message;

}
