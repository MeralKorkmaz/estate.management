package estate.management.com.payload.request;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPropertyKeyRequest {
    private String name;
    private Boolean built_in;
    private CategoryRequest category;
    private List<CategoryPropertyValueRequest> categoryPropertyValue = new ArrayList<>();
    public List<CategoryPropertyValueRequest> getCategoryPropertyValue() {
        return categoryPropertyValue;
    }

}