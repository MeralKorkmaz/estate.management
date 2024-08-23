package estate.management.com.payload.response.business;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotNull;
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CategoryPropertyValueResponse {
    private Long id;
    @NotNull
    private String value;
    @NotNull
    private Long advertId;
}