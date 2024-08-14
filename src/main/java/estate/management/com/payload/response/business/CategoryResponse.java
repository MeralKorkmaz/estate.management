package estate.management.com.payload.response.business;

import estate.management.com.domain.category.Category;
import estate.management.com.domain.category.CategoryPropertyKey;
import estate.management.com.domain.category.CategoryPropertyValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private Long id;
    private String title;
    private String icon;
    private Boolean built_in = false;
    private int seq;
    private String slug;
    private boolean active;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String name;
    private String value;


    public CategoryResponse(CategoryResponse createdCategory) {
    }

    public void setPropertyKeys(List<CategoryPropertyKey> propertyKeys) {
    }
}
