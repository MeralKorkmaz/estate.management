package estate.management.com.payload.request.user.abstracts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Pattern;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractUserRequest {


    //bu classa validation ekle

    private  String email;

    private String firstName;

    private String lastName;

    //@Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}", message = "phone format ; (XXX) XXX-XXXX' ")
    private String phone;




}
