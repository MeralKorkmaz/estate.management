package estate.management.com.payload.request;
import estate.management.com.domain.category.CategoryPropertyKey;
import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {
    @NotNull(message = "Title must not be empty")
    private String title;
    @NotNull(message = "Icon is required")
    private String icon;
    private Boolean built_in;
    @NotNull(message = "Seq must not be empty")
    private int seq;
    @NotNull(message = "Slug is required")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
    private String slug;
    private boolean isActive;
    private List<CategoryPropertyKey> categoryPropertyKey = new ArrayList<>();
    public boolean getIsActive() { return isActive;
    }
}