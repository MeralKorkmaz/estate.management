package estate.management.com.service.business;

import estate.management.com.domain.administrative.District;
import estate.management.com.payload.mapper.DistrictMapper;
import estate.management.com.payload.response.DistrictResponse;
import estate.management.com.repository.business.DistrictRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;

    public List<DistrictResponse> getAllDistricts() {
        return districtRepository.findAll()
                .stream()
                .map(districtMapper::mapDistrictToDistrictResponse)
                .collect(Collectors.toList());
    }

}
