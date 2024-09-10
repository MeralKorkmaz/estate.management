package estate.management.com.controller.business;

import estate.management.com.payload.response.DistrictResponse;
import estate.management.com.service.business.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DistrictController {

    private final DistrictService districtService;

    @Autowired
    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("/districts")
    public List<DistrictResponse> getAllDistricts() {
        return districtService.getAllDistricts();
    }

}
