package estate.management.com.controller.business;

import estate.management.com.payload.request.advert.AdvertRequest;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.payload.response.concrete.advert.AdvertResponse;
import estate.management.com.payload.response.concrete.advert.CategoryResponseForAdvert;
import estate.management.com.payload.response.concrete.advert.CityResponseForAdvert;
import estate.management.com.service.business.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adverts")
public class AdvertController {
//hey 

    private final AdvertService advertService;

    @PostMapping("/save")
    public ResponseEntity<AdvertResponse> saveAdvert(@Valid @RequestBody AdvertRequest advertRequest){

        return ResponseEntity.ok(advertService.saveAdvert(advertRequest));

    }

    @GetMapping("")
    public Page<AdvertResponse> getTheAdvertsByPage(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "advert_type_id", required = false) Integer advertTypeId,
            @RequestParam(value = "price_start", required = false) Double priceStart,
            @RequestParam(value = "price_end", required = false) Double priceEnd,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "title") String sort,
            @RequestParam(value = "type", defaultValue = "asc") String type
    ) {
        return advertService.getTheAdvertsByPage(q, advertTypeId, priceStart, priceEnd, location, status, page, size, sort, type);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityResponseForAdvert>> getCities(@RequestParam(name = "cityId") Long cityId) {

        return ResponseEntity.ok(advertService.getCities(cityId));
    }


    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponseForAdvert>> getCategories(@RequestParam(name = "categoryId") Long categoryId){
        return ResponseEntity.ok(advertService.getCategories(categoryId));
    }


    @GetMapping("/slug")
    public ResponseEntity<AdvertResponse> getAdvertBySlug(@Valid @RequestBody AdvertRequest request) {
        AdvertResponse advertResponse = advertService.getAdvertBySlug(request);
        return ResponseEntity.ok(advertResponse);
    }

    @GetMapping("/{advertId}/auth")
    public ResponseMessage<AdvertResponse> getAuthenticatedUserById(@PathVariable Long advertId, HttpServletRequest request){
        return advertService.getAuthenticatedUserById(advertId,request);
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
