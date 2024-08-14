package estate.management.com.service.business;

import estate.management.com.domain.category.Category;
import estate.management.com.domain.category.CategoryPropertyKey;
import estate.management.com.domain.category.CategoryPropertyValue;
import estate.management.com.exemption.ResourceNotFoundException;
import estate.management.com.payload.mappers.CategoryMapper;
import estate.management.com.payload.messages.ErrorMessages;
import estate.management.com.payload.messages.SuccessMessages;
import estate.management.com.payload.request.CategoryRequest;
import estate.management.com.payload.request.PropertyKeyRequest;
import estate.management.com.payload.request.PropertyValueRequest;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.payload.response.business.CategoryResponse;
import estate.management.com.repository.CategoryRepository;
import estate.management.com.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final PageableHelper pageableHelper;

    public Page<CategoryResponse> getActiveCategories(String title, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageableWithProperties(title, page, size, sort, type);
        if (title != null && !title.isEmpty()) {
            return categoryRepository.findByTitleContainingIgnoreCaseAndActive(title, true, pageable)
                    .map(categoryMapper::mapCategoryToCategoryResponse);
        } else {
            return categoryRepository.findByActive(true, pageable)
                    .map(categoryMapper::mapCategoryToCategoryResponse);
        }
    }

    public Page<CategoryResponse> getAllCategoriesByTitle(String title, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageableWithProperties(title, page, size, sort, type);
        if (title != null && !title.isEmpty()) {
            return categoryRepository.findByTitleContainingIgnoreCase(title, pageable)
                    .map(categoryMapper::mapCategoryToCategoryResponse);
        }
        return categoryRepository.findAll(pageable)
                .map(categoryMapper::mapCategoryToCategoryResponse);
    }

    public CategoryResponse findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessages.CATEGORY_NOT_FOUND_MESSAGE, id)));
        return categoryMapper.mapCategoryToCategoryResponse(category);
    }

    //public CategoryResponse createCategory( Category category) {
    //}

    public ResponseMessage<CategoryResponse> updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessages.CATEGORY_NOT_FOUND_MESSAGE, id)));

        if (Boolean.TRUE.equals(category.getBuilt_in())) {
            throw new IllegalStateException("Built-in categories cannot be updated.");
        }else{
        category.setTitle(categoryRequest.getTitle());
        category.setIcon(categoryRequest.getIcon());
        category.setSeq(categoryRequest.getSeq());
        category.setSlug(categoryRequest.getSlug());
        category.setActive(categoryRequest.getActive());
        category.setUpdateAt(categoryRequest.getUpdateAt());

        Category updatedCategory = categoryRepository.save(category);
        return ResponseMessage.<CategoryResponse>
                        builder()
                .message(SuccessMessages.CATEGORY_UPDATE)
                .object(categoryMapper.mapCategoryToCategoryResponse(updatedCategory))
                .status(HttpStatus.OK)
                .build();

    }}}





    