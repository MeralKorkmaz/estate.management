package estate.management.com.service.business;
import estate.management.com.domain.category.Category;
import estate.management.com.domain.category.CategoryPropertyKey;
import estate.management.com.domain.category.CategoryPropertyValue;
import estate.management.com.exception.OperationNotAllowedException;
import estate.management.com.exemption.ResourceNotFoundException;
import estate.management.com.payload.mappers.CategoryMapper;
import estate.management.com.payload.messages.ErrorMessages;
import estate.management.com.payload.messages.SuccessMessages;
import estate.management.com.payload.request.CategoryPropertyKeyRequest;
import estate.management.com.payload.request.CategoryPropertyValueRequest;
import estate.management.com.payload.request.CategoryRequest;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.payload.response.business.CategoryPropertyKeyResponse;
import estate.management.com.payload.response.business.CategoryResponse;
import estate.management.com.repository.CategoryPropertyKeyRepository;
import estate.management.com.repository.CategoryRepository;
import estate.management.com.repository.business.AdvertRepository;
import estate.management.com.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;
   // private final CategoryPropertyKeyRepository categoryPropertyKeyRepository;
//    private final CategoryPropertyValueRepository categoryPropertyValueRepository;
    private final AdvertRepository advertTypeRepository;
    private final CategoryMapper categoryMapper;
    private final PageableHelper pageableHelper;

    public Page<CategoryResponse> getIsActiveCategories(String title, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageableWithProperties(title, page, size, sort, type);
        if (title != null && !title.isEmpty()) {
            return categoryRepository.findByTitleContainingIgnoreCaseAndIsActive(title, true, pageable)
                    .map(categoryMapper::mapCategoryToCategoryResponse);
        } else {
            return categoryRepository.findByIsActive(true, pageable)
                    .map(categoryMapper::mapCategoryToCategoryResponse);
        }
    }

    public List<CategoryResponse> findByTitleContainingIgnoreCaseAndIsActive(String title, Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findByTitleContainingIgnoreCaseAndIsActive(title, true, pageable);

        if (categoryPage.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("No categories found with title '%s'", title));
        }

        return categoryPage.stream()
                .map(categoryMapper::mapCategoryToCategoryResponse)
                .collect(Collectors.toList());
    }



    public CategoryResponse findCategoryById(Long id) {
        Category category = categoryRepository.findCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessages.CATEGORY_NOT_FOUND_MESSAGE, id)));
        return categoryMapper.mapCategoryToCategoryResponse(category);
    }

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setTitle(categoryRequest.getTitle());
        category.setIcon(categoryRequest.getIcon());
        category.setSlug(categoryRequest.getSlug());
        category.setSeq(categoryRequest.getSeq());
        category.setBuilt_in(categoryRequest.getBuilt_in());
        category.setIsActive(categoryRequest.isActive());

            if (categoryRequest.getCategoryPropertyKey() != null) {
                for (CategoryPropertyKey keys : categoryRequest.getCategoryPropertyKey()) {
                    CategoryPropertyKey propertyKey = new CategoryPropertyKey();
                    propertyKey.setName(keys.getName());
                    propertyKey.setBuilt_in(false);
                    propertyKey.setCategoryPropertyValue(keys.getCategoryPropertyValue());
                    propertyKey.setCategory(category);

                    List<CategoryPropertyValue> propertyValues = new ArrayList<>();
                    if (keys.getCategoryPropertyValue() != null) {
                        for (CategoryPropertyValue valueObj : keys.getCategoryPropertyValue()) {
                            CategoryPropertyValue propertyValue = new CategoryPropertyValue();
                            propertyValue.setValue(valueObj.getValue());
                            propertyValue.setAdvertId(valueObj.getAdvertId());
                            propertyValue.setCategoryPropertyKey(propertyKey);
                            propertyValues.add(propertyValue);
                        }
                    }
                    propertyKey.setCategoryPropertyValue(propertyValues);
                    category.getCategoryPropertyKey().add(propertyKey);
                }
            }

            Category savedCategory = categoryRepository.save(category);
            return categoryMapper.mapCategoryToCategoryResponse(savedCategory);
        }


        public ResponseMessage<CategoryResponse> updateCategory (Long id, CategoryRequest categoryRequest){
            Category category = categoryRepository.findCategoryById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            String.format(ErrorMessages.CATEGORY_NOT_FOUND_MESSAGE, id)));

            if (Boolean.TRUE.equals(category.getBuilt_in())) {
                throw new IllegalStateException("Built-in categories cannot be updated.");
            } else {
                category.setTitle(categoryRequest.getTitle());
                category.setIcon(categoryRequest.getIcon());
                category.setSeq(categoryRequest.getSeq());
                category.setSlug(categoryRequest.getSlug());
                category.setIsActive(categoryRequest.isActive());

                Category updatedCategory = categoryRepository.save(category);
                return ResponseMessage.<CategoryResponse>builder()
                        .message(SuccessMessages.CATEGORY_UPDATE)
                        .object(categoryMapper.mapCategoryToCategoryResponse(updatedCategory))
                        .status(HttpStatus.OK)
                        .build();
            }
        }
    public ResponseMessage deleteById(Long id) {
        Category category = categoryRepository.findCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        if (category.getBuilt_in()) {
            throw new OperationNotAllowedException("Cannot delete built-in category");
        }
        if (advertTypeRepository.existsByCategoryId(id)) {
            throw new OperationNotAllowedException("Cannot delete category with related adverts");
        }

        categoryRepository.delete(category);
        CategoryResponse categoryResponse = categoryMapper.mapCategoryToCategoryResponse(category);
        return ResponseMessage.builder()
                .message(SuccessMessages.CATEGORY_DELETE)
                .object(categoryResponse)
                .status(HttpStatus.OK)
                .build();
    }


    public List<CategoryPropertyKeyResponse> getPropertyKeyOfCategory(Long categoryId) {
        Optional<Category> propertykeys = categoryRepository.findCategoryById(categoryId);
        if (propertykeys.isPresent()) {
            Category categorykeys = propertykeys.get();
            return categorykeys.getCategoryPropertyKey().stream()
                    .map(categoryMapper::mapCategoryPropertyKeyToCategoryPropertyKeyResponse)
                    .collect(Collectors.toList());

        }
        return new ArrayList<>();

    }


}