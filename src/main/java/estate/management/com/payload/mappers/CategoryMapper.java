package estate.management.com.payload.mappers;

import estate.management.com.domain.category.Category;
import estate.management.com.payload.request.CategoryRequest;
import estate.management.com.payload.response.business.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category mapCategoryRequestToCategories(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .title(categoryRequest.getTitle())
                .icon(categoryRequest.getIcon())
                .seq(categoryRequest.getSeq())
                .slug(categoryRequest.getSlug())
                .isActive(categoryRequest.isActive())
                .createAt(categoryRequest.getCreateAt())
                .updateAt(categoryRequest.getUpdateAt())
                .build();
        return category;
    }

    public CategoryResponse mapCategoryToCategoryResponse(Category category) {
        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .icon(category.getIcon())
                .seq(category.getSeq())
                .slug(category.getSlug())
                .isActive(category.isActive())
                .createAt(category.getCreateAt())
                .updateAt(category.getUpdateAt())
                .build();
        return categoryResponse;
    }
}
