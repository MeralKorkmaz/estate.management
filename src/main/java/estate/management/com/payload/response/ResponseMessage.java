package estate.management.com.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL) //null degerler Json'da gorulmeyecek.

public class ResponseMessage <T>{

    private T object;
    private String message;
    private HttpStatus status;
}
