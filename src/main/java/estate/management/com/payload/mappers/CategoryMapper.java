package estate.management.com.payload.mappers;

import estate.management.com.domain.category.Category;
import estate.management.com.payload.request.CategoryRequest;
import estate.management.com.payload.response.business.CategoryResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CategoryMapper {

    public Category mapCategoryRequestToCategories(CategoryRequest categoryRequest) {
        return Category.builder()
                .title(categoryRequest.getTitle())
                .icon(categoryRequest.getIcon())
                .seq(categoryRequest.getSeq())
                .isActive(categoryRequest.isActive())
                .build();
    }

    public CategoryResponse mapCategoryToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .icon(category.getIcon())
                .seq(category.getSeq())
                .isActive(category.isActive())
                .build();

    }
}
