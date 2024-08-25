package estate.management.com.controller.business;

import estate.management.com.payload.request.concrete.advert.AdvertRequest;
import estate.management.com.payload.request.concrete.advert.AdvertRequestForCity;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.payload.response.concrete.advert.AdvertResponse;
import estate.management.com.payload.response.concrete.advert.AdvertResponseForCity;
import estate.management.com.service.business.AdvertService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adverts")
public class AdvertController {


    private final AdvertService advertService;

    @GetMapping("")
    public Page<AdvertResponse> getTheAdvertsByPage(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "category_id", required = false) Integer categoryId,
            @RequestParam(value = "advert_type_id", required = false) Integer advertTypeId,
            @RequestParam(value = "price_start", required = false) Double priceStart,
            @RequestParam(value = "price_end", required = false) Double priceEnd,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "category_id") String sort,
            @RequestParam(value = "type", defaultValue = "asc") String type
    ) {
        return advertService.getTheAdvertsByPage(q, categoryId, advertTypeId, priceStart, priceEnd, location, status, page, size, sort, type);
    }

    @GetMapping("/cities/{cityId}")
    public ResponseEntity<AdvertResponseForCity> getCities(@PathVariable Long cityId) {
        AdvertResponseForCity response = advertService.getCities(cityId);
        return ResponseEntity.ok(response);
    }

    // http://localhost:8080/adverts/1/admin
    @GetMapping("/{advertId}/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<AdvertResponse> getAdvertByAdminAndManager(@PathVariable Long advertId){
        return ResponseEntity.ok(advertService.getAdvertByAdminAndManager(advertId));
    }

    // http://localhost:8080/adverts/admin/1
    @DeleteMapping("/admin/{advertId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseMessage<String> deleteAdvertByAdminAndManager(@PathVariable Long advertId){
        return advertService.deleteAdvertByAdminAndManager(advertId);
    }

    // http://localhost:8080/adverts/auth/1
    @PutMapping("/auth/{advertId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseMessage<AdvertResponse> updateAdvertByAuthenticatedUser(HttpServletRequest httpServletRequest,
                                                                           @PathVariable Long advertId,
                                                                           @RequestBody AdvertRequest advertRequest){

        return advertService.updateAdvertByAuthenticatedUser(httpServletRequest, advertId, advertRequest);

    }

    // http://localhost:8080/adverts/admin/1
    @PutMapping("/admin/{advertId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseMessage<AdvertResponse> updateAdvertByAdminAndManager(@PathVariable Long advertId,
                                                                           @RequestBody AdvertRequest advertRequest){

        return advertService.updateAdvertByAdminAndManager(advertId, advertRequest);

    }


}