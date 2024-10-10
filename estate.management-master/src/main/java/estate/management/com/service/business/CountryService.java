package estate.management.com.service.business;

import estate.management.com.domain.administrative.Country;
import estate.management.com.payload.mapper.CountryMapper;
import estate.management.com.payload.response.CountryResponse;
import estate.management.com.repository.business.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    //Get all Countries
    public List<CountryResponse> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(countryMapper::mapCountryToCountryResponse)
                .collect(Collectors.toList());
    }

}
