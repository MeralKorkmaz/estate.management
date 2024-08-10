package estate.management.com.controller.business;

import estate.management.com.domain.advert.AdvertType;
import estate.management.com.service.business.AdvertTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdvertTypeController {

    private final AdvertTypeService advertTypeService;

    @Autowired
    public AdvertTypeController(AdvertTypeService advertTypeService) {
        this.advertTypeService = advertTypeService;
    }

    @GetMapping
    public List<AdvertType> getAllAdvertTypes() {
        return advertTypeService.getAllAdvertTypes();
    }
}
