package estate.management.com.service.business;
import estate.management.com.domain.category.Category;
import estate.management.com.domain.category.CategoryPropertyKey;
import estate.management.com.domain.category.CategoryPropertyValue;
import estate.management.com.exception.OperationNotAllowedException;
import estate.management.com.exception.ResourceNotFoundException;
import estate.management.com.payload.mapper.CategoryMapper;
import estate.management.com.payload.message.ErrorMessages;
import estate.management.com.payload.message.SuccessMessages;
import estate.management.com.payload.request.CategoryRequest;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.payload.response.business.CategoryResponse;
import estate.management.com.repository.business.CategoryPropertyKeyRepository;
import estate.management.com.repository.business.CategoryPropertyValueRepository;
import estate.management.com.repository.business.AdvertRepository;
import estate.management.com.repository.business.CategoryRepository;
import estate.management.com.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryPropertyKeyRepository categoryPropertyKeyRepository;
    private final CategoryPropertyValueRepository categoryPropertyValueRepository;
    private final AdvertRepository advertRepository;
    private final CategoryMapper categoryMapper;
    private final PageableHelper pageableHelper;



    public Page<Category> getAllActiveCategories(Pageable pageable) {
        return categoryRepository.findByIsActive(true, pageable);
    }

    public List<CategoryResponse> getCategories(String q, Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findByTitleContainingIgnoreCaseAndIsActive(q, true, pageable);
        if (categoryPage.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("No categories found with title '%s'", q));}
        return categoryPage.stream()
                .map(categoryMapper::mapCategoryToCategoryResponse)
                .collect(Collectors.toList());}


    public CategoryResponse findCategoryById(Long id) {
        Category category = categoryRepository.findCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Category with ID %d not found", id)));
        return categoryMapper.mapCategoryToCategoryResponse(category);}

    public ResponseMessage<CategoryResponse> createCategory(CategoryRequest categoryRequest) {
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
                        propertyValue.setAdvert(valueObj.getAdvert());
                        propertyValues.add(propertyValue);}}
                propertyKey.setCategoryPropertyValue(propertyValues);
                category.getCategoryPropertyKey().add(propertyKey);}}
        Category savedCategory = categoryRepository.save(category);
        CategoryResponse categoryResponse = categoryMapper.mapCategoryToCategoryResponse(savedCategory);
        return ResponseMessage.<CategoryResponse>builder()
                .message(SuccessMessages.CATEGORY_CREATED_SUCCESS)
                .object(categoryResponse)
                .status(HttpStatus.OK)
                .build();}

    public ResponseMessage<CategoryResponse> updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessages.CATEGORY_GENERAL_NOT_FOUND_ERROR, id)));
        if (Boolean.TRUE.equals(category.getBuilt_in())) {
            throw new IllegalStateException("Built-in true cannot be updated.");
        } else {
            category.setTitle(categoryRequest.getTitle());
            category.setIcon(categoryRequest.getIcon());
            category.setSeq(categoryRequest.getSeq());
            category.setSlug(categoryRequest.getSlug());
            category.setIsActive(categoryRequest.isActive());
            Category updatedCategory = categoryRepository.save(category);
            return ResponseMessage.<CategoryResponse>builder()
                    .message(SuccessMessages.CATEGORY_UPDATED_SUCCESS)
                    .object(categoryMapper.mapCategoryToCategoryResponse(updatedCategory))
                    .status(HttpStatus.OK)
                    .build();}}

    public ResponseMessage deleteById(Long id) {
        Category category = categoryRepository.findCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        if (category.getBuilt_in()) {
            throw new OperationNotAllowedException("Cannot delete built-in true category");}

        if (advertRepository.existsByCategory(category)) {
            throw new OperationNotAllowedException("Cannot delete category with related adverts");
        }

        categoryRepository.delete(category);

        CategoryResponse categoryResponse = categoryMapper.mapCategoryToCategoryResponse(category);
        return ResponseMessage.builder()
                .message(SuccessMessages.CATEGORY_DELETED_SUCCESS)
                .object(categoryResponse)
                .status(HttpStatus.OK)
                .build();}


    public CategoryResponse getCategoryBySlug(String slug) {
        Category category = categoryRepository.findBySlugIgnoreCase(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with slug: " + slug));

        return categoryMapper.mapCategoryToCategoryResponse(category);
    }

}



