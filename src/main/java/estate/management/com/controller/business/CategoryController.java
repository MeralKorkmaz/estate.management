package estate.management.com.controller.business;

import estate.management.com.payload.request.CategoryRequest;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.payload.response.business.CategoryResponse;
import estate.management.com.service.business.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    // C01-Endpoint to fetch active categories with optional title filtering and pagination
    @GetMapping("/active")
    public ResponseEntity<Page<CategoryResponse>> getActiveCategories(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "type", defaultValue = "asc") String type) {
        Page<CategoryResponse> categories = categoryService.getActiveCategories(title, page, size, sort, type);
        return ResponseEntity.ok(categories);
    }

    //C02- Endpoint to fetch all categories with optional title filtering and pagination
    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<Page<CategoryResponse>> getAllCategoriesByTitle(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "type", defaultValue = "asc") String type) {
        Page<CategoryResponse> categories = categoryService.getAllCategoriesByTitle(title, page, size, sort, type);
        return ResponseEntity.ok(categories);
    }

    //C03- Endpoint to fetch a single category by its ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id) {
        CategoryResponse categoryResponse = categoryService.findById(id);
        return ResponseEntity.ok(categoryResponse);
    }
    // C04-Endpoint to create a new category--did not work

    //@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    //    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    //    public ResponseEntity<CategoryResponse> createCategory(@RequestBody Category category) {
    //        CategoryResponse createdCategory = categoryService.createCategory(category);
    //        CategoryResponse response = new CategoryResponse(createdCategory);
    //        return ResponseEntity.ok(response);
    //    }
 //C05 Update By Id
    @PutMapping("/update/{id}")//does not work properly
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseMessage<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
            ResponseMessage<CategoryResponse> categoryResponse = categoryService.updateCategory(id, categoryRequest);
            return categoryService.updateCategory(id,categoryRequest);
        }}
