package estate.management.com.payload.request.abstracts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractUserRequest {


    //bu classa validation ekle

    private  String email;

    private String firstName;

    private String lastName;

    private String phone;




}
