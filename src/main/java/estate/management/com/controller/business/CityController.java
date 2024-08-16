package estate.management.com.controller.business;

import estate.management.com.domain.administrative.City;
import estate.management.com.payload.response.CityResponse;
import estate.management.com.service.business.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities")
    public List<CityResponse> getAllCities() {
        return cityService.getAllCities();
    }
    
}
