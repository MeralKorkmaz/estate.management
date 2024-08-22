package estate.management.com.controller.business;

import estate.management.com.domain.administrative.Country;
import estate.management.com.payload.response.CountryResponse;
import estate.management.com.service.business.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public List<CountryResponse> getAllCountries() {
        return countryService.getAllCountries();
    }
}
