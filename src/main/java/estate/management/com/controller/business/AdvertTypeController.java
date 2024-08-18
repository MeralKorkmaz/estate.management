package estate.management.com.controller.business;

import estate.management.com.domain.advert.AdvertType;
import estate.management.com.payload.request.AdvertTypeRequest;
import estate.management.com.payload.response.AdvertTypeResponse;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.service.business.AdvertTypeService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdvertTypeController {

    private final AdvertTypeService advertTypeService;

    //Get All AdvertTypes
    @GetMapping("/advert-types")
    public List<AdvertTypeResponse> getAllAdvertTypes() {
        return advertTypeService.getAllAdvertTypes();
    }

    //Get AdvertType by ID
    @GetMapping("/advert-types/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public AdvertTypeResponse getAdvertTypeById(@PathVariable Long id) {
        return advertTypeService.getAdvertTypeById(id);
    }

    //Create AdvertType
    @PostMapping("/advert-types")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseMessage<AdvertTypeResponse> createAdvertType(
            @RequestBody @Valid AdvertTypeRequest advertTypeRequest) {
        return advertTypeService.createAdvertType(advertTypeRequest);
    }



}
