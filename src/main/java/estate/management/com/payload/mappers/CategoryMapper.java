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
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {
    public Category mapCategoryRequestToCategory(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .id(categoryRequest.getId())
                .title(categoryRequest.getTitle())
                .icon(categoryRequest.getIcon())
                .built_in(categoryRequest.getBuilt_in())
                .seq(categoryRequest.getSeq())
                .slug(categoryRequest.getSlug())
                .isActive(categoryRequest.isActive())
                .build();

        if (categoryRequest.getCategoryPropertyKey() != null) {
            for (CategoryPropertyKeyRequest keyRequest : categoryRequest.getCategoryPropertyKey()) {
                CategoryPropertyKey key = mapCategoryPropertyKeyRequestToCategoryPropertyKey(keyRequest);
                key.setCategory(category);

                if (keyRequest.getCategoryPropertyValue() != null) {
                    List<CategoryPropertyValue> propertyValues = new ArrayList<>();
                    for (CategoryPropertyValueRequest valueRequest : keyRequest.getCategoryPropertyValue()) {
                        CategoryPropertyValue propertyValue = mapCategoryPropertyValueRequestToCategoryPropertyValue(valueRequest);
                        propertyValue.setCategoryPropertyKey(key);
                        propertyValues.add(propertyValue);
                    }
                    key.setCategoryPropertyValue(propertyValues);
                }
                category.addCategoryPropertyKey(key);}}
        return category;}
    public CategoryPropertyKey mapCategoryPropertyKeyRequestToCategoryPropertyKey(CategoryPropertyKeyRequest keyRequest) {
        return CategoryPropertyKey.builder()
                .id(keyRequest.getId())
                .name(keyRequest.getName())
                .built_in(keyRequest.getBuilt_in())
                .build();}
    public CategoryPropertyValue mapCategoryPropertyValueRequestToCategoryPropertyValue(CategoryPropertyValueRequest valueRequest) {
        return CategoryPropertyValue.builder()
                .id(valueRequest.getId())
                .value(valueRequest.getValue())
                .advertId(valueRequest.getAdvertId())
                .build();}
    public CategoryPropertyKeyResponse mapCategoryPropertyKeyToCategoryPropertyKeyResponse(CategoryPropertyKey categoryPropertyKey) {
        List<CategoryPropertyValueResponse> propertyValueResponses = new ArrayList<>();

        if (categoryPropertyKey.getCategoryPropertyValue() != null) {
            for (CategoryPropertyValue value : categoryPropertyKey.getCategoryPropertyValue()) {
                CategoryPropertyValueResponse valueResponse = mapCategoryPropertyValueToCategoryPropertyValueResponse(value);
                propertyValueResponses.add(valueResponse);}}
        return CategoryPropertyKeyResponse.builder()
                .id(categoryPropertyKey.getId())
                .name(categoryPropertyKey.getName())
                .built_in(categoryPropertyKey.getBuilt_in())
                .categoryPropertyValue(categoryPropertyKey.getCategoryPropertyValue())
                .build();}
    public CategoryPropertyValueResponse mapCategoryPropertyValueToCategoryPropertyValueResponse(CategoryPropertyValue categoryPropertyValue) {
        return CategoryPropertyValueResponse.builder()
                .id(categoryPropertyValue.getId())
                .value(categoryPropertyValue.getValue())
                .advertId(categoryPropertyValue.getAdvertId())
                .build();}
    public CategoryResponse mapCategoryToCategoryResponse(Category category) {
        List<CategoryPropertyKeyResponse> propertyKeyResponses = new ArrayList<>();

        if (category.getCategoryPropertyKey() != null) {
            for (CategoryPropertyKey key : category.getCategoryPropertyKey()) {
                List<CategoryPropertyValueResponse> propertyValueResponses = new ArrayList<>();

                if (key.getCategoryPropertyValue() != null) {
                    for (CategoryPropertyValue value : key.getCategoryPropertyValue()) {
                        CategoryPropertyValueResponse valueResponse = mapCategoryPropertyValueToCategoryPropertyValueResponse(value);
                        propertyValueResponses.add(valueResponse);}}
                CategoryPropertyKeyResponse keyResponse = CategoryPropertyKeyResponse.builder()
                        .id(key.getId())
                        .name(key.getName())
                        .categoryPropertyValue(key.getCategoryPropertyValue()) // Use the correct type here
                        .build();
                propertyKeyResponses.add(keyResponse);}}
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
    public Category mapCategoryRequestToCategoryForUpdate(Long id, CategoryRequest categoryRequest) {
        Category category = mapCategoryRequestToCategory(categoryRequest);
        category.setId(id);
        return category;
    }
}