package estate.management.com.controller.business;

import estate.management.com.payload.request.AdvertRequest;
import estate.management.com.payload.response.AdvertResponse;
import estate.management.com.repository.business.AdvertRepository;
import estate.management.com.service.business.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/adverts")
public class AdvertController {


    public final AdvertService advertService;

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

}
