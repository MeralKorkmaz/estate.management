package estate.management.com.payload.mapper;

import estate.management.com.domain.administrative.City;
import estate.management.com.domain.advert.Advert;
import estate.management.com.payload.request.concrete.advert.AdvertRequest;
import estate.management.com.payload.response.concrete.CityResponse;
import estate.management.com.payload.response.concrete.advert.AdvertResponse;
import estate.management.com.payload.response.concrete.advert.AdvertResponseForCity;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.List;

@Data
@Component
public class AdvertMapper {


    public AdvertResponse mapAdvertToAdvertResponse(Advert advert){
        return AdvertResponse.builder()
                .advertTypeId(advert.getAdvertTypeId())
                .id(advert.getId())
                .price(advert.getPrice())
                .slug(advert.getSlug())
                .countryId(advert.getCountryId())
                .districtId(advert.getDistrictId())
                .title(advert.getTitle())
                .categoryId(advert.getCategoryId())
                .location(advert.getLocation())
                .description(advert.getDescription())
                .status(advert.getStatus())
                .viewCount(advert.getViewCount())
                .userId(advert.getUserId())
                .build();

    }
    public Advert mapAdvertRequestToAdvert(AdvertRequest advertRequest){
        return Advert.builder()
                .advertTypeId(advertRequest.getAdvertTypeId())
                .built_in(advertRequest.getBuilt_in())
                .price(advertRequest.getPrice())

                .slug(advertRequest.getSlug())
                .countryId(advertRequest.getCountryId())
                .districtId(advertRequest.getDistrictId())
                .title(advertRequest.getTitle())
                .categoryId(advertRequest.getCategoryId())
                .location(advertRequest.getLocation())
                .description(advertRequest.getDescription())
                .status(advertRequest.getStatus())
                .viewCount(advertRequest.getViewCount())
                .build();
    }

    public CityResponse mapCityToCityResponse(City city) {
        return CityResponse.builder()
                .city(city.getName())
                .build();
    }

    public AdvertResponseForCity mapCitiesToAdvertCityResponse(List<CityResponse> cityResponses) {
        return AdvertResponseForCity.builder()
                .cityResponses(cityResponses.toArray(new CityResponse[0]))
                .build();
    }

}
