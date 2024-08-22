
package estate.management.com.service.business;

import estate.management.com.domain.administrative.City;
import estate.management.com.exception.ResourceNotFoundException;
import estate.management.com.payload.mapper.AdvertMapper;
import estate.management.com.payload.message.ErrorMessages;
import estate.management.com.payload.response.concrete.CityResponse;
import estate.management.com.payload.response.concrete.advert.AdvertResponse;
import estate.management.com.payload.response.concrete.advert.AdvertResponseForCity;
import estate.management.com.repository.business.AdvertRepository;
import estate.management.com.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertService {

    private final AdvertRepository advertRepository;
    private final PageableHelper pageableHelper;
    private final AdvertMapper advertMapper;

    public Page<AdvertResponse> getTheAdvertsByPage(String q, Integer categoryId, Integer advertTypeId,
                                                    Double priceStart, Double priceEnd, String location,
                                                    Integer status, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageable(page, size, sort, type);
        return advertRepository.findAdvertsByCriteria(q, categoryId, advertTypeId, priceStart, priceEnd, location, status, pageable)
                .map(advertMapper::mapAdvertToAdvertResponse);
    }

    public AdvertResponseForCity getCities(Long cityId) {
        List<CityResponse> cityResponses = advertRepository.findCityCountsByCityId(cityId);
        if (cityResponses.isEmpty()) {
            throw new ResourceNotFoundException(String.format(ErrorMessages.ADVERT_CITY_ID_NOT_FOUND_ERROR, cityId));
        }
        return AdvertResponseForCity.builder()
                .cityResponses(cityResponses.toArray(new CityResponse[0]))  // Change this if needed
                .build();
    }

}
