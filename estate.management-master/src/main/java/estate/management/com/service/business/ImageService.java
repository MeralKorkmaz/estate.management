package estate.management.com.service.business;

import estate.management.com.domain.Image;
import estate.management.com.domain.advert.Advert;
import estate.management.com.exception.ResourceNotFoundException;
import estate.management.com.payload.mapper.ImageMapper;
import estate.management.com.payload.message.ErrorMessages;
import estate.management.com.payload.request.Image.ImageRequestForAdvert;
import estate.management.com.payload.response.image.ImageResponse;
import estate.management.com.payload.response.image.ImageResponseForAdvert;
import estate.management.com.repository.business.AdvertRepository;
import estate.management.com.repository.business.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final AdvertRepository advertRepository;
    private final ImageMapper imageMapper;

    public ImageResponseForAdvert uploadImage(Long advertId, ImageRequestForAdvert imageRequest) {
        // Fetch the Advert entity from the repository using the advertId
        Advert advert = advertRepository.findById(advertId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessages.IMAGE_ADVERT_ID_NOT_FOUND_ERROR, advertId)));

        // Now set the Advert to the ImageRequest
        Image image = imageMapper.toImageEntity(imageRequest);
        image.setAdvert(advert);  // Set the fetched Advert entity

        // Save the image to the repository
        Image savedImage = imageRepository.save(image);

        // Convert and return the saved Image as ImageResponseForAdvert
        return imageMapper.toImageResponse(savedImage);
    }






    public ImageResponseForAdvert getImageById(Long imageId) {

        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.IMAGE_ID_NOT_FOUND_ERROR,imageId)));


        return imageMapper.toImageResponse(image);
    }
}
