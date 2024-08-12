package estate.management.com.service.business;

import estate.management.com.domain.category.Category;
import estate.management.com.exemption.ResourceNotFoundException;
import estate.management.com.payload.mappers.CategoryMapper;
import estate.management.com.payload.messages.ErrorMessages;
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

    // Fetch active categories with optional title filtering and pagination
    public Page<CategoryResponse> getActiveCategories(String title, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);

        if (title != null && !title.isEmpty()) {
            return categoryRepository.findByTitleContainingIgnoreCaseAndActive(title, 1, pageable)
                    .map(categoryMapper::mapCategoryToCategoryResponse);
        } else {
            return categoryRepository.findByActive(1, pageable)
                    .map(categoryMapper::mapCategoryToCategoryResponse);
        }
    }

    // Fetch all categories with optional title filtering and pagination (Admin)
    public Page<Category> getAllCategories(String title, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageableWithProperties(title, page, size, sort, type);

        if (title != null && !title.isEmpty()) {
            return categoryRepository.findByTitleContainingIgnoreCase(title, pageable);
        }

        return categoryRepository.findAll(pageable);
    }

    // Find a category by its ID and map it to CategoryResponse
    public CategoryResponse findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessages.CATEGORY_NOT_FOUND_MESSAGE, id)));
        return categoryMapper.mapCategoryToCategoryResponse(category);
    }
}