package estate.management.com.controller.business;
import estate.management.com.payload.request.CategoryPropertyKeyRequest;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.payload.response.business.CategoryPropertyKeyResponse;
import estate.management.com.service.business.CategoryPropertyKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories/properties")
@RequiredArgsConstructor
public class CategoryPropertyKeyController {
    @Autowired
    private CategoryPropertyKeyService categoryPropertyKeyService;

    //C07 Get property key of a category
    @GetMapping("/{id}/properties")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<List<CategoryPropertyKeyResponse>> getCategoryPropertyKeys(@PathVariable Long id) {
        List<CategoryPropertyKeyResponse> propertyKeys = categoryPropertyKeyService.findCategoryPropertyKeys(id);
        return ResponseEntity.ok(propertyKeys);
    }
   // ________________________________________________________
    //C08 create properties of a category
    @PostMapping("/{id}/properties")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<CategoryPropertyKeyResponse> createCategoryPropertyKey(
            @PathVariable Long id,
            @RequestBody CategoryPropertyKeyRequest request) {
        CategoryPropertyKeyResponse response = categoryPropertyKeyService.createCategoryPropertyKey(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    //___________________________________________________________________
    //C09 Update

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<ResponseMessage<CategoryPropertyKeyResponse>> updateCategoryPropertyKey(
            @Valid @PathVariable Long id,
            @RequestBody CategoryPropertyKeyRequest keyRequest) {

        ResponseMessage<CategoryPropertyKeyResponse> updatedKey = categoryPropertyKeyService.updateCategoryPropertyKey(id, keyRequest);
        return new ResponseEntity<>(updatedKey, HttpStatus.OK);
    }
//_________________________________________________________________________
//   C10 Delete
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public ResponseEntity<Map<String, Object>> deletePropertyKey(@PathVariable Long id) {
        CategoryPropertyKeyResponse deletedKey = categoryPropertyKeyService.deletePropertyKey(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Property key deleted successfully");
        response.put("deletedPropertyKey", deletedKey);
        return ResponseEntity.ok(response);
    }
}
