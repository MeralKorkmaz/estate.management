package estate.management.com.payload.request;
import estate.management.com.domain.category.Category;
import estate.management.com.domain.category.CategoryPropertyValue;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPropertyKeyRequest {
    private Long id;
    private String name;
    private Boolean built_in = false;
    private Category category;
    private List<CategoryPropertyValueRequest> categoryPropertyValue;
}