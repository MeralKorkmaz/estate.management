package estate.management.com.payload.mappers;
import estate.management.com.domain.category.Category;
import estate.management.com.domain.category.CategoryPropertyKey;
import estate.management.com.domain.category.CategoryPropertyValue;
import estate.management.com.payload.request.CategoryPropertyValueRequest;
import estate.management.com.payload.request.CategoryRequest;
import estate.management.com.payload.response.business.CategoryPropertyKeyResponse;
import estate.management.com.payload.response.business.CategoryPropertyValueResponse;
import estate.management.com.payload.response.business.CategoryResponse;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
@Data
public class CategoryMapper {
    public CategoryRequest mapCategoryToCategoryRequest(Category category) {
        return CategoryRequest.builder()
                .title(category.getTitle())
                .icon(category.getIcon())
                .seq(category.getSeq())
                .slug(category.getSlug())
                .isActive(category.isActive())
                .categoryPropertyKey(category.getCategoryPropertyKey())
                .build();}
    public CategoryResponse mapCategoryToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .icon(category.getIcon())
                .seq(category.getSeq())
                .slug(category.getSlug())
                .isActive(category.isActive())
                .build();}
    public CategoryPropertyKeyResponse mapCategoryPropertyKeyToCategoryPropertyKeyResponse(CategoryPropertyKey categoryPropertyKey) {
        return CategoryPropertyKeyResponse.builder()
                .id(categoryPropertyKey.getId())
                .name(categoryPropertyKey.getName())
                .built_in(categoryPropertyKey.getBuilt_in())
                .categoryPropertyValue(
                        Optional.ofNullable(categoryPropertyKey.getCategoryPropertyValue())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(this::mapCategoryPropertyValueToCategoryPropertyValueResponse)
                                .collect(Collectors.toList())
                )
                .build();}
    public CategoryPropertyValueResponse mapCategoryPropertyValueToCategoryPropertyValueResponse(CategoryPropertyValue categoryPropertyValue) {
        return CategoryPropertyValueResponse.builder()
                .id(categoryPropertyValue.getId())
                .value(categoryPropertyValue.getValue())
                .advertId(categoryPropertyValue.getAdvertId())
                .build();}
    public CategoryPropertyValueRequest mapCategoryPropertyValueToCategoryPropertyValueRequest(CategoryPropertyValue categoryPropertyValue) {
        return CategoryPropertyValueRequest.builder()
                .id(categoryPropertyValue.getId())
                .value(categoryPropertyValue.getValue())
                .advertId(categoryPropertyValue.getAdvertId())
                .build();}
    public List<CategoryPropertyValueRequest> mapToCategoryPropertyValueRequestList(List<CategoryPropertyValue> propertyValues) {
        return propertyValues.stream()
                .map(this::mapCategoryPropertyValueToCategoryPropertyValueRequest)
                .collect(Collectors.toList());
    }

    public List<CategoryPropertyKeyResponse> mapToCategoryPropertyKeyResponseList(List<CategoryPropertyKey> propertyKeys) {
        return propertyKeys.stream()
                .map(this::mapCategoryPropertyKeyToCategoryPropertyKeyResponse)
                .collect(Collectors.toList());
    }}