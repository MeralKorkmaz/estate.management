package estate.management.com.payload.request;
import estate.management.com.domain.category.CategoryPropertyKey;
import estate.management.com.payload.request.abstracts.BaseCategoryRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest extends BaseCategoryRequest {
    @NotNull(message = "Title must not be empty")
    private String title;
    @NotNull(message = "Icon is required")
    private String icon;
    private Boolean built_in = false;
    @NotNull(message = "Seq must not be empty")
    private int seq;
    @NotNull(message = "Slug is required")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
    private String slug;
    private boolean isActive=true;
    private List<CategoryPropertyKeyRequest> categoryPropertyKey = new ArrayList<>();

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }



}