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
    public Page<AdvertResponse>getTheAdvertsByPage (
            @RequestParam(value = "page")  int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "type") String type
    ){

        return  advertService.getTheAdvertsByPage(page, size, sort, type);
    }

}
