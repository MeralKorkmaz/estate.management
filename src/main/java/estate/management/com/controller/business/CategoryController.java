package estate.management.com.controller.business;

import estate.management.com.domain.category.Category;
import estate.management.com.payload.response.business.CategoryResponse;
import estate.management.com.service.business.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    // Endpoint to fetch active categories with optional title filtering and pagination
    @GetMapping("/{active}")
    public Page<CategoryResponse> getActiveCategories(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "category_id") String sort,
            @RequestParam(value = "type", defaultValue = "asc") String type) {
        return categoryService.getActiveCategories(title, page, size, sort, type);
    }

    // Endpoint to fetch all categories with optional title filtering and pagination (Admin only)
    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public Page<Category> getAllCategories(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "category_id") String sort,
            @RequestParam(value = "type", defaultValue = "asc") String type) {
        return categoryService.getAllCategories(title, page, size, sort, type);
    }

    // Endpoint to fetch a single category by its ID
    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id);
    }
}