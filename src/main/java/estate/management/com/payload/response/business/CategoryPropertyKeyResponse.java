package estate.management.com.payload.response.business;
import com.fasterxml.jackson.annotation.JsonInclude;
import estate.management.com.domain.category.Category;
import lombok.Builder;
import lombok.Data;
import java.util.List;
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CategoryPropertyKeyResponse {
    private Long id;
    private String name;
    private Boolean built_in;
    private Category category;
    private List<CategoryPropertyValueResponse> categoryPropertyValue;

}