package estate.management.com.payload.request;
import estate.management.com.domain.category.Category;
import estate.management.com.domain.category.CategoryPropertyValue;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPropertyKeyRequest {
    private String name;
    private Boolean built_in = false;
    private Category category;
    private List<CategoryPropertyValue> categoryPropertyValue;
}