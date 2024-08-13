package estate.management.com.controller.business;

import estate.management.com.domain.advert.AdvertType;
import estate.management.com.service.business.AdvertTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdvertTypeController {

    private final AdvertTypeService advertTypeService;

    @Autowired
    public AdvertTypeController(AdvertTypeService advertTypeService) {
        this.advertTypeService = advertTypeService;
    }

    @GetMapping("/advert-types")
    public List<AdvertType> getAllAdvertTypes() {
        return advertTypeService.getAllAdvertTypes();
    }

    @GetMapping("/advert-types/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public AdvertType getAdvertTypeById(@PathVariable Long id) {
        return advertTypeService.getAdvertTypeById(id);
    }

}
