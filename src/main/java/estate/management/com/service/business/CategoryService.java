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
    @Autowired
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final PageableHelper pageableHelper;


    public Page<CategoryResponse> getActiveCategories(String title, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageableWithProperties(title, page, size, sort, type);

        if (title != null && !title.isEmpty()) {
            return categoryRepository.findByTitleContainingIgnoreCaseAndActive(title, 1, pageable)
                    .map(categoryMapper::mapCategoryToCategoryResponse);
        } else {
            return categoryRepository.findByActive(1, pageable)
                    .map(categoryMapper::mapCategoryToCategoryResponse);
        }
    }
    public Page<Category> getAllCategories(String title, int page, int size, String sort, String type) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(type), sort));

        if (title != null && !title.isEmpty()) {
            return categoryRepository.findByTitleContainingIgnoreCase(title, pageRequest);
        }

        return categoryRepository.findAll(pageRequest);
    }

    public CategoryResponse findById(Long id) {
        Category category=isCategoryExist(id);
        return categoryMapper.mapCategoryToCategoryResponse(category);
        
    }

    public Category isCategoryExist(Long id) {
        return categoryRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessages.CATEGORY_NOT_FOUND_MESSAGE,id)));
    }
}