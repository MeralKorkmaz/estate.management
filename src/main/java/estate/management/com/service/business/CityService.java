package estate.management.com.service.business;

import estate.management.com.domain.administrative.City;
import estate.management.com.payload.mapper.CityMapper;
import estate.management.com.payload.response.CityResponse;
import estate.management.com.repository.business.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    //Get All Cities
    public List<CityResponse> getAllCities() {
        return cityRepository.findAll()
                .stream()
                .map(cityMapper::mapCityToCityResponse)
                .collect(Collectors.toList());
    }
}
