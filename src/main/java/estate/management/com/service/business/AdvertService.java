

package estate.management.com.service.business;

import estate.management.com.domain.advert.Advert;
import estate.management.com.domain.user.User;
import estate.management.com.exception.ResourceNotFoundException;
import estate.management.com.payload.mapper.AdvertMapper;
import estate.management.com.payload.message.ErrorMessages;
import estate.management.com.payload.message.SuccessMessages;
import estate.management.com.payload.request.advert.AdvertRequest;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.payload.response.concrete.advert.CategoryResponseForAdvert;
import estate.management.com.payload.response.concrete.advert.CityResponseForAdvert;
import estate.management.com.payload.response.concrete.advert.AdvertResponse;
import estate.management.com.repository.UserRepository;
import estate.management.com.repository.business.AdvertRepository;
import estate.management.com.repository.business.CategoryPropertyKeyRepository;
import estate.management.com.repository.business.ImageRepository;
import estate.management.com.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertService {
    private final AdvertRepository advertRepository;
    private final PageableHelper pageableHelper;
    private final AdvertMapper advertMapper;
    private final ImageRepository imageRepository;
    private final CategoryPropertyKeyRepository categoryPropertyKeyRepository;

    private final UserRepository userRepository;

    private String generateSlug(String input) {
        String noWhiteSpace = Pattern.compile("\\s").matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(noWhiteSpace, Normalizer.Form.NFD);
        return Pattern.compile("[^\\w-]").matcher(normalized).replaceAll("").toLowerCase();
    }


    public AdvertResponse saveAdvert(AdvertRequest advertRequest) {
        String slug = advertRequest.getSlug();
        if (slug == null || slug.isEmpty()) {
            slug = generateSlug(advertRequest.getTitle());
        }


        if (advertRepository.existsBySlug(slug)) {
            throw new RuntimeException("Slug already exists");
        }

        Advert advert = advertMapper.toAdvert(advertRequest);
        advert.setSlug(slug); // Set the generated or provided slug
        Advert savedAdvert = advertRepository.save(advert);
        return advertMapper.toAdvertResponse(savedAdvert);
    }

    public Page<AdvertResponse> getTheAdvertsByPage(String q, Integer advertTypeId,
                                                    Double priceStart, Double priceEnd, String location,
                                                    Integer status, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageable(page, size, sort, type);
        return advertRepository.findAdvertsByCriteria(q, advertTypeId, priceStart, priceEnd, location, status, pageable)
                .map(advertMapper::toAdvertResponse);
    }

    public List<CityResponseForAdvert> getCities(Long cityId) {
       return  advertRepository.countAdvertsByCity();
    }



    public List<CategoryResponseForAdvert> getCategories(Long categoryId) {

        return advertRepository.countAdvertsByCategory();
    }

    public AdvertResponse getAdvertBySlug(AdvertRequest request) {

        String slug = request.getSlug();

        Advert advert = advertRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessages.ADVERT_SLUG_NOT_FOUND_ERROR, slug)));

        return advertMapper.toAdvertResponse(advert);
    }



    public List<AdvertResponse> getMostPopularAdverts(Long amount) {
        int pageSize = amount != null ? amount.intValue() : 10; // Default to 10 if amount is null
        return advertRepository.findMostPopularAdverts(PageRequest.of(0, pageSize))
                .stream()
                .map(advertMapper::toAdvertResponse)
                .collect(Collectors.toList());
    }



    public List<AdvertResponse> getUserAdverts(Authentication authentication, int page, int size, String sort, String type) {
        // Extract email from Authentication object
        String email = (String) authentication.getPrincipal();

        // Fetch user ID based on the email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        int userId = user.getId().intValue(); // Assuming User entity has a getId() method

        Pageable pageable = PageRequest.of(page, size,
                "asc".equalsIgnoreCase(type) ? Sort.by(sort).ascending() : Sort.by(sort).descending());

        Page<Advert> advertPage = advertRepository.findByUserId(userId, pageable);
        return advertPage.getContent().stream()
                .map(advertMapper::toAdvertResponse)
                .collect(Collectors.toList());
    }

    public List<AdvertResponse> getAdverts(
            String query,
            Integer categoryId,
            Integer advertTypeId,
            Double priceStart,
            Double priceEnd,
            Integer status,
            Pageable pageable // Accept Pageable here
    ) {
        // Use the Pageable to fetch the correct page of results
        Page<Advert> advertPage = advertRepository.findByCriteria(query, categoryId, advertTypeId, priceStart, priceEnd, status, pageable);

        // Convert the Page of Advert entities to a List of AdvertResponse DTOs
        return advertPage.stream()
                .map(advertMapper::toAdvertResponse)
                .collect(Collectors.toList());
    }

    public ResponseMessage<AdvertResponse> getAuthenticatedUserById(Long advertId, HttpServletRequest request) {

       String email = (String) request.getAttribute("email");
       if(!userRepository.existsByEmailEquals(email)){
           return ResponseMessage.<AdvertResponse>builder()
                   .status(HttpStatus.NOT_FOUND)
                   .message(String.format(ErrorMessages.USER_NOT_FOUND_USER_MESSAGE, email))
                   .build();

       }else {


           Advert advertToGet = advertRepository.findById(advertId).orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.ADVERT_ID_NOT_FOUND_ERROR,advertId)));

          User user = userRepository.findByEmailEquals(email);

           if (advertToGet.getUserId() != user.getId()) {
               return ResponseMessage.<AdvertResponse>builder()
                       .status(HttpStatus.FORBIDDEN)
                       .message(String.format(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE,user))
                       .build();
           }


           AdvertResponse advertResponse = advertMapper.toAdvertResponse(advertToGet);

           return ResponseMessage.<AdvertResponse>builder()
                   .status(HttpStatus.OK)
                   .message(String.format(SuccessMessages.ADVERT_FOUND_SUCCESS,advertId))
                   .object(advertResponse)
                   .build();

       }
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
