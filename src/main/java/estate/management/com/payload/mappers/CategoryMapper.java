package estate.management.com.payload.mappers;

import estate.management.com.domain.category.Category;
import estate.management.com.domain.category.CategoryPropertyKey;
import estate.management.com.domain.category.CategoryPropertyValue;
import estate.management.com.payload.request.PropertyKeyRequest;
import estate.management.com.payload.request.PropertyValueRequest;
import estate.management.com.payload.response.business.CategoryResponse;
import estate.management.com.payload.response.business.PropertyKeyResponse;
import estate.management.com.payload.response.business.PropertyValueResponse;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public Category mapCategoryRequestToCategory(Category categoryRequest) {
        return Category.builder()
                .title(categoryRequest.getTitle())
                .icon(categoryRequest.getIcon())
                .seq(categoryRequest.getSeq())
                .slug(categoryRequest.getSlug())
                .active(categoryRequest.getActive())
                .createAt(categoryRequest.getCreateAt())
                .updateAt(categoryRequest.getUpdateAt())

                .build();
    }

    public CategoryResponse mapCategoryToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .title(category.getTitle())
                .icon(category.getIcon())
                .seq(category.getSeq())
                .slug(category.getSlug())
                .active(category.getActive())
                .createAt(category.getCreateAt())
                .updateAt(category.getUpdateAt())
                .build();

    }
    public Category mapCategoryRequestToCategoryForUpdate(Long id,Category categoryRequest){
        Category term =  mapCategoryRequestToCategory(categoryRequest);
        term.setId(id);
        return term;
    }
}