package estate.management.com.service.business;

import estate.management.com.domain.category.Category;
import estate.management.com.exemption.ResourceNotFoundException;
import estate.management.com.payload.mappers.CategoryMapper;
import estate.management.com.payload.messages.ErrorMessages;
import estate.management.com.payload.request.CategoryRequest;
import estate.management.com.payload.response.business.CategoryResponse;
import estate.management.com.repository.CategoryRepository;
import estate.management.com.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
            return categoryRepository.findByIsActive(true, pageable)
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

    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.mapCategoryRequestToCategories(categoryRequest);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.mapCategoryToCategoryResponse(savedCategory);
    }
}
