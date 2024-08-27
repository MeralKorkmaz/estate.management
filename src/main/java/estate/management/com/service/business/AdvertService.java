

package estate.management.com.service.business;

import estate.management.com.domain.Image;
import estate.management.com.domain.administrative.City;
import estate.management.com.domain.advert.Advert;
import estate.management.com.domain.category.CategoryPropertyKey;
import estate.management.com.domain.user.User;
import estate.management.com.exception.ResourceNotFoundException;
import estate.management.com.payload.mapper.AdvertMapper;
import estate.management.com.payload.message.ErrorMessages;
import estate.management.com.payload.message.SuccessMessages;
import estate.management.com.payload.request.ImageRequest;
import estate.management.com.payload.request.advert.AdvertRequest;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.payload.response.concrete.CityResponse;
import estate.management.com.payload.response.concrete.advert.AdvertResponse;
import estate.management.com.payload.response.concrete.advert.AdvertResponseForCity;
import estate.management.com.repository.UserRepository;
import estate.management.com.repository.business.AdvertRepository;
import estate.management.com.repository.business.CategoryPropertyKeyRepository;
import estate.management.com.repository.business.ImageRepository;
import estate.management.com.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertService {
    private final AdvertRepository advertRepository;
    private final PageableHelper pageableHelper;
    private final AdvertMapper advertMapper;
    private final ImageRepository imageRepository;
    private final CategoryPropertyKeyRepository categoryPropertyKeyRepository;

    private final UserRepository userRepository;

    public AdvertResponse saveAdvert(AdvertRequest advertRequest) {
        Advert advert = AdvertMapper.toAdvert(advertRequest);
        Advert savedAdvert = advertRepository.save(advert);
        return AdvertMapper.toAdvertResponse(savedAdvert);
    }

    public Page<AdvertResponse> getTheAdvertsByPage(String q, Integer advertTypeId,
                                                    Double priceStart, Double priceEnd, String location,
                                                    Integer status, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageable(page, size, sort, type);
        return advertRepository.findAdvertsByCriteria(q, advertTypeId, priceStart, priceEnd, location, status, pageable)
                .map(AdvertMapper::toAdvertResponse);
    }

    public AdvertResponseForCity getCities(int cityId) {
        List<CityResponse> cityResponses = advertRepository.findCityCountsByCityId(cityId);
        if (cityResponses.isEmpty()) {
            throw new ResourceNotFoundException(String.format(ErrorMessages.ADVERT_CITY_ID_NOT_FOUND_ERROR, cityId));
        }
        return AdvertResponseForCity.builder()
                .cityResponses(cityResponses.toArray(new CityResponse[0]))
                .build();
    }


    public AdvertResponse getAdvertByAdminAndManager(Long advertId) {

        Advert advert = advertRepository.findById(advertId).orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.ADVERT_ID_NOT_FOUND_ERROR,advertId)));

        AdvertResponse advertResponse = advertMapper.toAdvertResponse(advert);

        return advertResponse;

    }

    public ResponseMessage<String> deleteAdvertByAdminAndManager(Long advertId) {

        Advert advert = advertRepository.findById(advertId).orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.ADVERT_ID_NOT_FOUND_ERROR,advertId)));

        if (advert.getBuilt_in()){
            return ResponseMessage.<String>builder()
                    .message(ErrorMessages.BUILT_IN_IMMUTABLE_ERROR)
                    .status(HttpStatus.OK)
                    .build();
        } else{

            // TODO If any advert is deleted, related records in tour_requests, favorites,
            //logs, images,
            //category,property_values also should
            //be deleted.

            advertRepository.deleteById(advert.getId());

            return ResponseMessage.<String>builder()
                    .message(SuccessMessages.ADVERT_DELETED_SUCCESS)
                    .status(HttpStatus.OK)
                    .build();

        }

    }


    public ResponseMessage<AdvertResponse> updateAdvertByAuthenticatedUser(HttpServletRequest httpServletRequest, Long advertId, AdvertRequest advertRequest) {

        String email = (String) httpServletRequest.getAttribute("email");

        if (!userRepository.existsByEmailEquals(email)){
            return ResponseMessage.<AdvertResponse>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(String.format(ErrorMessages.USER_NOT_FOUND_USER_MESSAGE, email))
                    .build();
        }

        Advert advertToUpdate = advertRepository.findById(advertId).orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.ADVERT_ID_NOT_FOUND_ERROR,advertId)));


        if (advertToUpdate.getBuilt_in()){
            return ResponseMessage.<AdvertResponse>builder()
                    .status(HttpStatus.OK)
                    .message(String.format(ErrorMessages.BUILT_IN_IMMUTABLE_ERROR))
                    .build();
        }

        User user = userRepository.findByEmailEquals(email);

        if (advertToUpdate.getUserId() != user.getId()){
            return ResponseMessage.<AdvertResponse>builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .message(String.format(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE))
                    .build();
        }

        // TODO properties kısmı entity'de yok
        advertToUpdate.setTitle(advertRequest.getTitle());
        advertToUpdate.setDescription(advertRequest.getDescription());
        advertToUpdate.setPrice(advertRequest.getPrice());
        advertToUpdate.setAdvertTypeId(advertRequest.getAdvertTypeId());
        advertToUpdate.setCountryId(advertRequest.getCountryId());
        advertToUpdate.setCityId(advertRequest.getCityId());
        advertToUpdate.setDistrictId(advertRequest.getDistrictId());
        // TODO pending'de kaldığı için aktif olmayacak. Admin veya manager active yapacak. Meetingde sor.
        advertToUpdate.setActive(false);
        advertToUpdate.setStatus(0);


        // Save the updated advert
        advertRepository.save(advertToUpdate);

        // Map the updated advert to AdvertResponse
        AdvertResponse advertResponse = advertMapper.toAdvertResponse(advertToUpdate);

        // Return the response with the updated advert
        return ResponseMessage.<AdvertResponse>builder()
                .status(HttpStatus.OK)
                .message("Advert updated successfully.")
                .object(advertResponse)
                .build();


    }

    public ResponseMessage<AdvertResponse> updateAdvertByAdminAndManager(Long advertId, AdvertRequest advertRequest) {

        Advert advertToUpdate = advertRepository.findById(advertId).orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.ADVERT_ID_NOT_FOUND_ERROR,advertId)));


        if (advertToUpdate.getBuilt_in()){
            return ResponseMessage.<AdvertResponse>builder()
                    .status(HttpStatus.OK)
                    .message(String.format(ErrorMessages.BUILT_IN_IMMUTABLE_ERROR))
                    .build();
        }

        // TODO properties kısmı entity'de yok
        advertToUpdate.setTitle(advertRequest.getTitle());
        advertToUpdate.setDescription(advertRequest.getDescription());
        advertToUpdate.setPrice(advertRequest.getPrice());
        advertToUpdate.setAdvertTypeId(advertRequest.getAdvertTypeId());
        advertToUpdate.setCountryId(advertRequest.getCountryId());
        advertToUpdate.setCityId(advertRequest.getCityId());
        advertToUpdate.setDistrictId(advertRequest.getDistrictId());
        // TODO pending'de kaldığı için aktif olmayacak. Admin veya manager active yapacak. Meetingde sor.
        advertToUpdate.setActive(false);
        advertToUpdate.setStatus(0);


        // Save the updated advert
        advertRepository.save(advertToUpdate);

        // Map the updated advert to AdvertResponse
        AdvertResponse advertResponse = advertMapper.toAdvertResponse(advertToUpdate);

        // Return the response with the updated advert
        return ResponseMessage.<AdvertResponse>builder()
                .status(HttpStatus.OK)
                .message("Advert updated successfully.")
                .object(advertResponse)
                .build();



    }

}