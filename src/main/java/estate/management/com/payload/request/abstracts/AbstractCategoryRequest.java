package estate.management.com.payload.request.abstracts;
import estate.management.com.domain.category.CategoryPropertyKey;
import estate.management.com.payload.request.CategoryPropertyKeyRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.List;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractCategoryRequest {

    private Long id;
    private String title;
    private String icon;
    private Boolean built_in = false;
    private int seq;
    private String slug;
    private boolean isActive;
    private List<CategoryPropertyKeyRequest> categoryPropertyKey;

}