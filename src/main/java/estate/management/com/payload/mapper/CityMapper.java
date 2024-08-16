package estate.management.com.payload.mapper;

import estate.management.com.domain.administrative.City;
import estate.management.com.payload.request.CityRequest;
import estate.management.com.payload.response.CityResponse;
import estate.management.com.repository.business.CityRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
public class CityMapper {

    public City mapCityRequestToCity(CityRequest cityRequest) {
        return City.builder()
                .name(cityRequest.getTitle())
                .build();
    }

    public CityResponse mapCityToCityResponse(City city) {
        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .build();
    }

}
