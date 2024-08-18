package estate.management.com.controller.business;
import estate.management.com.payload.request.CategoryRequest;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.payload.response.business.CategoryResponse;
import estate.management.com.service.business.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    @GetMapping("/active")
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
    public List<CategoryResponse> getAllCategoriesByTitle(@RequestParam(name="title") String title){
        return categoryService.getAllCategoriesByTitle(title);

    }


    //C03- Endpoint to fetch a single category by its ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id) {
        CategoryResponse categoryResponse = categoryService.findById(id);
        return ResponseEntity.ok(categoryResponse);
    }
    // C04-Endpoint to create a new category

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")//cannot see created values on Postman.
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest baseCategoryRequest) {
        CategoryResponse createdCategory = categoryService.createCategory(baseCategoryRequest);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

 //C05 Update By Id
    @PutMapping("/update/{id}")//cannot see updated value on postman
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseMessage<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
            ResponseMessage<CategoryResponse> categoryResponse = categoryService.updateCategory(id, categoryRequest);
            return categoryService.updateCategory(id,categoryRequest);
        }
        //C06 Deleting by Id
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<CategoryResponse> deleteById(@PathVariable Long id){;
    CategoryResponse deletedCategory=categoryService.deleteCategory(id);
    return new ResponseEntity<>(deletedCategory,HttpStatus.OK);}

}
