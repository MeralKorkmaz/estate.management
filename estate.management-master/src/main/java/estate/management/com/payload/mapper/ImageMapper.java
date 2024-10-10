package estate.management.com.payload.mapper;

import estate.management.com.domain.Image;
import estate.management.com.domain.advert.Advert;
import estate.management.com.payload.request.Image.ImageRequest;
import estate.management.com.payload.request.Image.ImageRequestForAdvert;
import estate.management.com.payload.response.image.ImageResponse;
import estate.management.com.payload.response.image.ImageResponseForAdvert;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Data
public class ImageMapper {


    public Image toImageEntity(ImageRequestForAdvert imageRequest) {
        return Image.builder()
                .name(imageRequest.getName())
                .type(imageRequest.getType())
                .featured(imageRequest.isFeatured())
                .data(imageRequest.getData())
                .id(imageRequest.getAdvertId())
                .build();
    }


    public ImageResponseForAdvert toImageResponse(Image image) {
        return ImageResponseForAdvert.builder()
                .imageId(image.getId())
                .name(image.getName())
                .type(image.getType())
                .featured(image.isFeatured())
                .data(image.getData())
                .advertId(image.getAdvert().getId()) // Assuming the Advert has an ID field
                .build();
    }
}
