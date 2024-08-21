package estate.management.com.controller.business;

import estate.management.com.domain.advert.AdvertType;
import estate.management.com.service.business.AdvertTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdvertTypeController {

    private final AdvertTypeService advertTypeService;

    @Autowired
    public AdvertTypeController(AdvertTypeService advertTypeService) {
        this.advertTypeService = advertTypeService;
    }

    @GetMapping("/advert-types")
    public ResponseEntity<List<AdvertType>> getAllAdvertTypes() {
        return ResponseEntity.status(HttpStatus.OK).body(advertTypeService.getAllAdvertTypes());
    }

    @GetMapping("/advert-types/{id}")
   // @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<AdvertType> getAdvertTypeById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(advertTypeService.getAdvertTypeById(id));
    }

    @PostMapping("/advert-types")
   // @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<AdvertType> createAdvertType(@Validated @RequestBody AdvertType advertType) {
        AdvertType createdAdvertType = advertTypeService.createAdvertType(advertType);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdvertType);
    }

}
