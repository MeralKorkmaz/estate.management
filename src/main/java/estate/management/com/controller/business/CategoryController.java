package estate.management.com.controller.business;
import estate.management.com.payload.request.CategoryRequest;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.payload.response.business.CategoryResponse;
import estate.management.com.service.business.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    // C01-Endpoint to fetch active categories with optional title filtering and pagination
    @GetMapping("")
    public ResponseEntity<Page<CategoryResponse>> getActiveCategories(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "type", defaultValue = "asc") String type) {
        Page<CategoryResponse> categories = categoryService.getIsActiveCategories(title, page, size, sort, type);
        return ResponseEntity.ok(categories);
    }
    //C02- Endpoint to fetch all categories with optional title filtering and pagination
    @GetMapping("/title")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<List<CategoryResponse>> getCategories(
            @RequestParam(required = false) String q,
            Pageable pageable) {
        List<CategoryResponse> categoryResponses = categoryService.getCategories(q, pageable);
        return ResponseEntity.ok(categoryResponses);
    }
    //C03- Endpoint to fetch a single category by its ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable Long id) {
        CategoryResponse category = categoryService.findCategoryById(id);
        return ResponseEntity.ok(category);
    }
    // C04-Endpoint to create a new category
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<ResponseMessage<CategoryResponse>> createCategory(@RequestBody CategoryRequest categoryRequest) {
        ResponseMessage<CategoryResponse> createdCategoryResponse = categoryService.createCategory(categoryRequest);
        return new ResponseEntity<>(createdCategoryResponse, HttpStatus.CREATED);
    }
 //C05 Update&Save By Id
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseMessage<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
            ResponseMessage<CategoryResponse> categoryResponse = categoryService.updateCategory(id, categoryRequest);
            return categoryService.updateCategory(id,categoryRequest);
        }
        //C06 Deleting by Id
    @DeleteMapping("/delete/{id}")// I need to test with AdverId
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<ResponseMessage> deleteById(@PathVariable Long id) {
        ResponseMessage response = categoryService.deleteById(id);
        return new ResponseEntity<>(response, response.getStatus());
    }
    //C011
    @GetMapping("/slug/{slug}")
    @PreAuthorize("permitAll()")  // Allows all users to access this endpoint
    public ResponseEntity<CategoryResponse> getCategoryBySlug(@PathVariable String slug) {
        CategoryResponse category = categoryService.getCategoryBySlug(slug);
        return ResponseEntity.ok(category);
    }


}



