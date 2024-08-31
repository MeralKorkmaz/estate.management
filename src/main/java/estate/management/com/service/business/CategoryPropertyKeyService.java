package estate.management.com.service.business;
import estate.management.com.domain.category.Category;
import estate.management.com.domain.category.CategoryPropertyKey;
import estate.management.com.domain.category.CategoryPropertyValue;
import estate.management.com.exception.ResourceNotFoundException;
import estate.management.com.payload.mapper.CategoryMapper;
import estate.management.com.payload.message.SuccessMessages;
import estate.management.com.payload.request.CategoryPropertyKeyRequest;
import estate.management.com.payload.request.CategoryPropertyValueRequest;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.payload.response.business.CategoryPropertyKeyResponse;
import estate.management.com.repository.business.CategoryRepository;
import estate.management.com.repository.business.CategoryPropertyKeyRepository;
import estate.management.com.repository.business.CategoryPropertyValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryPropertyKeyService {
    private final CategoryPropertyKeyRepository categoryPropertyKeyRepository;
    private final CategoryPropertyValueRepository categoryPropertyValueRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryPropertyKeyResponse> findCategoryPropertyKeys(Long id) {
        List<CategoryPropertyKey> propertyKeys = categoryRepository.findPropertyKeysByCategoryId(id);
        if (propertyKeys.isEmpty()) {
            throw new ResourceNotFoundException("No property keys found for category ID: " + id);}
        return categoryMapper.mapToCategoryPropertyKeyResponseList(propertyKeys);}

    public CategoryPropertyKeyResponse createCategoryPropertyKey(Long categoryId, CategoryPropertyKeyRequest request) {
        Category category = categoryRepository.findCategoryById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));
        CategoryPropertyKey categoryPropertyKey = new CategoryPropertyKey();
        categoryPropertyKey.setName(request.getName());
        categoryPropertyKey.setBuilt_in(request.getBuilt_in());
        categoryPropertyKey.setCategory(category);
        List<CategoryPropertyValue> values = new ArrayList<>();
        for (CategoryPropertyValueRequest valueRequest : request.getCategoryPropertyValue()) {
            CategoryPropertyValue value = new CategoryPropertyValue();
            value.setValue(valueRequest.getValue());
            value.setAdvert(valueRequest.getAdvert());
            value.setCategoryPropertyKey(categoryPropertyKey);
            values.add(value);
        }
        categoryPropertyKey.setCategoryPropertyValue(values);
        CategoryPropertyKey savedPropertyKey = categoryPropertyKeyRepository.save(categoryPropertyKey);
        return categoryMapper.mapCategoryPropertyKeyToCategoryPropertyKeyResponse(savedPropertyKey);
    }
    @Transactional
    public CategoryPropertyKeyResponse deletePropertyKey(Long id) {
        CategoryPropertyKey categoryPropertyKey = categoryPropertyKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property key not found"));
        if (categoryPropertyKey.getBuilt_in()) {
            throw new IllegalArgumentException("Built-in property keys cannot be deleted");}
        categoryPropertyValueRepository.deleteAllByCategoryPropertyKey(categoryPropertyKey);
        categoryPropertyKeyRepository.delete(categoryPropertyKey);
        return categoryMapper.mapCategoryPropertyKeyToCategoryPropertyKeyResponse(categoryPropertyKey);
    }
    @Transactional
    public ResponseMessage<CategoryPropertyKeyResponse> updateCategoryPropertyKey(Long keyId, CategoryPropertyKeyRequest keyRequest) {
        CategoryPropertyKey existingKey = categoryPropertyKeyRepository.findPropertyKeyById(keyId)
                .orElseThrow(() -> new ResourceNotFoundException("CategoryPropertyKey not found for this id :: " + keyId));
        if (existingKey.getBuilt_in()) {
            throw new IllegalArgumentException("Cannot update built-in true CategoryPropertyKey.");}
        existingKey.setName(keyRequest.getName());
        existingKey.setBuilt_in(keyRequest.getBuilt_in());
        Map<Long, CategoryPropertyValue> existingValuesMap = existingKey.getCategoryPropertyValue().stream()
                .collect(Collectors.toMap(CategoryPropertyValue::getId, Function.identity()));
        List<CategoryPropertyValue> updatedValues = new ArrayList<>();
        for (CategoryPropertyValueRequest valueRequest : keyRequest.getCategoryPropertyValue()) {
            CategoryPropertyValue value;
            if (valueRequest.getId() != null && existingValuesMap.containsKey(valueRequest.getId())) {
                value = existingValuesMap.get(valueRequest.getId());
                value.setValue(valueRequest.getValue());
                value.setAdvert(valueRequest.getAdvert());
            } else {
                value = new CategoryPropertyValue();
                value.setValue(valueRequest.getValue());
                value.setAdvert(valueRequest.getAdvert());
                value.setCategoryPropertyKey(existingKey);}
            updatedValues.add(value);}
        existingKey.getCategoryPropertyValue().clear();
        existingKey.getCategoryPropertyValue().addAll(updatedValues);
        CategoryPropertyKey savedKey = categoryPropertyKeyRepository.save(existingKey);
        CategoryPropertyKeyResponse responseKey = categoryMapper.mapCategoryPropertyKeyToCategoryPropertyKeyResponse(savedKey);
        return ResponseMessage.<CategoryPropertyKeyResponse>builder()
                .message(SuccessMessages.CATEGORY_PROPERTY_KEY_UPDATE)
                .object(responseKey)
                .status(HttpStatus.OK)
                .build();
    }}









