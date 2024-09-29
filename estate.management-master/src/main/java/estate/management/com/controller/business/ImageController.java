package estate.management.com.controller.business;


import estate.management.com.payload.request.Image.ImageRequest;
import estate.management.com.payload.request.Image.ImageRequestForAdvert;
import estate.management.com.payload.response.image.ImageResponse;
import estate.management.com.payload.response.image.ImageResponseForAdvert;
import estate.management.com.service.business.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


    @PostMapping("/{advertId}")
    public ResponseEntity<ImageResponseForAdvert> uploadImage(
            @PathVariable Long advertId,
            @RequestBody ImageRequestForAdvert imageRequest) {
        ImageResponseForAdvert response = imageService.uploadImage(advertId, imageRequest);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{imageId}")
    public ResponseEntity<ImageResponseForAdvert> getImageById(@PathVariable Long imageId) {

        ImageResponseForAdvert imageResponse = imageService.getImageById(imageId);
        return ResponseEntity.ok(imageResponse);
    }


}
