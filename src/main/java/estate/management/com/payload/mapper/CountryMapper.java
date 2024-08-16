package estate.management.com.payload.mapper;

import estate.management.com.domain.administrative.Country;
import estate.management.com.payload.request.CountryRequest;
import estate.management.com.payload.response.CountryResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CountryMapper {

    public Country mapCountryRequestToCountry(CountryRequest countryRequest) {
        return Country.builder()
                .name(countryRequest.getName())
                .build();
    }

    public CountryResponse mapCountryToCountryResponse(Country country) {
        return CountryResponse.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }

}
