package estate.management.com.payload.response.abstracts;

import estate.management.com.domain.category.CategoryPropertyKey;
import estate.management.com.payload.response.business.CategoryPropertyKeyResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

//@SuperBuilder
@Data
@AllArgsConstructor
@SuperBuilder
public abstract class AbstractCategoryResponse {
    private Long id;
    private String title;
    private String icon;
    private Boolean built_in=false;
    private int seq;
    private String slug;
    private boolean isActive=true;
    private List<CategoryPropertyKey> categoryPropertyKey;
}
