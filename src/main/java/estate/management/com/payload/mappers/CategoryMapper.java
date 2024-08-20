package estate.management.com.payload.mappers;
import estate.management.com.domain.category.Category;
import estate.management.com.domain.category.CategoryPropertyKey;
import estate.management.com.domain.category.CategoryPropertyValue;
import estate.management.com.payload.request.CategoryPropertyKeyRequest;
import estate.management.com.payload.request.CategoryPropertyValueRequest;
import estate.management.com.payload.request.CategoryRequest;
import estate.management.com.payload.response.business.CategoryPropertyKeyResponse;
import estate.management.com.payload.response.business.CategoryPropertyValueResponse;
import estate.management.com.payload.response.business.CategoryResponse;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class CategoryMapper {
    public Category mapCategoryRequestToCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .title(categoryRequest.getTitle())
                .icon(categoryRequest.getIcon())
                .built_in(categoryRequest.getBuilt_in())
                .seq(categoryRequest.getSeq())
                .slug(categoryRequest.getSlug())
                .isActive(categoryRequest.isActive())
                .categoryPropertyKey(categoryRequest.getCategoryPropertyKey())
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
                .categoryPropertyKey(category.getCategoryPropertyKey())
                .build();}
    public CategoryPropertyKey mapCategoryPropertyKeyRequestToCategoryPropertyKey(CategoryPropertyKeyRequest keyRequest) {
        return CategoryPropertyKey.builder()
                .name(keyRequest.getName())
                .built_in(keyRequest.getBuilt_in())
                .category(keyRequest.getCategory())
                .categoryPropertyValue(keyRequest.getCategoryPropertyValue())
                .build();}
    public CategoryPropertyValue mapCategoryPropertyValueRequestToCategoryPropertyValue(CategoryPropertyValueRequest valueRequest) {
        return CategoryPropertyValue.builder()
                .value(valueRequest.getValue())
                .advertId(valueRequest.getAdvertId())
                .build();}
    public CategoryPropertyKeyResponse mapCategoryPropertyKeyToCategoryPropertyKeyResponse(CategoryPropertyKey categoryPropertyKey) {
       return CategoryPropertyKeyResponse.builder()
               .id(categoryPropertyKey.getId())
               .name(categoryPropertyKey.getName())
               .categoryPropertyValue(categoryPropertyKey.getCategoryPropertyValue())
               .build();}
    public CategoryPropertyValueResponse mapCategoryPropertyValueToCategoryPropertyValueResponse(CategoryPropertyValue categoryPropertyValue) {
        return CategoryPropertyValueResponse.builder()
                .id(categoryPropertyValue.getId())
                .value(categoryPropertyValue.getValue())
                .advertId(categoryPropertyValue.getAdvertId())
                .build();}

    public Category mapCategoryRequestToCategoryForUpdate(Long id, CategoryRequest categoryRequest) {
        Category category = mapCategoryRequestToCategory(categoryRequest);
        category.setId(id);
        return category;
    }
}