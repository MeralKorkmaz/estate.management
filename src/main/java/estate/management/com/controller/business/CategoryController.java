package estate.management.com.controller.business;

import estate.management.com.domain.category.Category;
import estate.management.com.payload.response.business.CategoryResponse;
import estate.management.com.service.business.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/getActiveCategories")
    public Page<CategoryResponse> getActiveCategories(
            @RequestParam(value="title") String title,
            @RequestParam(value="page", defaultValue="0") int page,
            @RequestParam(value="size", defaultValue = "20") int size,
            @RequestParam(value="sort", defaultValue = "category_id") String sort,
            @RequestParam(value="type", defaultValue = "asc") String type )
    {return categoryService.getActiveCategories(title, page,size,sort,type);}
    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public Page<Category> getAllCategories(
            @RequestParam(value = "q", required = false) String title,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "category_id") String sort,
            @RequestParam(value = "type", defaultValue = "asc") String type) {

        return categoryService.getAllCategories(title, page, size, sort, type);
    }}