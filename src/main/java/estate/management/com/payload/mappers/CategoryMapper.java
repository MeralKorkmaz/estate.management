package estate.management.com.payload.mappers;
import estate.management.com.domain.category.Category;
import estate.management.com.domain.category.CategoryPropertyKey;
import estate.management.com.payload.request.CategoryRequest;
import estate.management.com.payload.response.business.CategoryPropertyKeyResponse;
import estate.management.com.payload.response.business.CategoryResponse;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;
@Component
@Data
public class CategoryMapper {
    public CategoryRequest mapCategoryToCategoryRequest(Category category) {
        return CategoryRequest.builder()
                .title(category.getTitle())
                .icon(category.getIcon())
                .built_in(category.getBuilt_in())
                .seq(category.getSeq())
                .slug(category.getSlug())
                .isActive(category.isActive())
                .categoryPropertyKey(category.getCategoryPropertyKey())
                .build();
    }
    public CategoryResponse mapCategoryToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .icon(category.getIcon())
                .built_in(category.getBuilt_in())
                .seq(category.getSeq())
                .slug(category.getSlug())
                .isActive(category.isActive())
                .build();
    }
    public CategoryPropertyKeyResponse mapCategoryPropertyKeyToCategoryPropertyKeyResponse(CategoryPropertyKey categoryPropertyKey) {
        return CategoryPropertyKeyResponse.builder()
                .id(categoryPropertyKey.getId())
                .name(categoryPropertyKey.getName())
                .categoryPropertyValue(categoryPropertyKey.getCategoryPropertyValue())
                .build();
    }
    public List<CategoryPropertyKeyResponse> mapToCategoryPropertyKeyResponseList(List<CategoryPropertyKey> propertyKeys) {
        return propertyKeys.stream()
                .map(this::mapCategoryPropertyKeyToCategoryPropertyKeyResponse)
                .collect(Collectors.toList());
    }
}
//    public CategoryPropertyValueResponse mapCategoryPropertyValueToCategoryPropertyValueResponse(CategoryPropertyValue categoryPropertyValue) {
//        return CategoryPropertyValueResponse.builder()
//                .id(categoryPropertyValue.getId())
//                .value(categoryPropertyValue.getValue())
//                .advertId(categoryPropertyValue.getAdvertId())
//                .build();}
//
//    public Category mapCategoryRequestToCategoryForUpdate(Long id, CategoryRequest categoryRequest) {
//        Category category = mapCategoryRequestToCategory(categoryRequest);
//        category.setId(id);
//        return category;
//    }
//}public CategoryPropertyKey mapCategoryPropertyKeyRequestToCategoryPropertyKey(CategoryPropertyKeyRequest keyRequest) {
//    return CategoryPropertyKey.builder()
//            .name(keyRequest.getName())
//            .built_in(keyRequest.getBuilt_in())
//            .category(keyRequest.getCategory())
//            .categoryPropertyValue(keyRequest.getCategoryPropertyValue())
//            .build();}
//    public CategoryPropertyValue mapCategoryPropertyValueRequestToCategoryPropertyValue(CategoryPropertyValueRequest valueRequest) {
//        return CategoryPropertyValue.builder()
//                .value(valueRequest.getValue())
//                .advertId(valueRequest.getAdvertId())
//                .build();}}