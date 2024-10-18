package estate.management.com.payload.mapper;

import estate.management.com.domain.administrative.District;
import estate.management.com.payload.request.DistrictRequest;
import estate.management.com.payload.response.DistrictResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class DistrictMapper {

    public District mapDistrictRequestToDistrict(DistrictRequest districtRequest) {
        return District.builder()
                .name(districtRequest.getName())
                .build();
    }

    public DistrictResponse mapDistrictToDistrictResponse(District district) {
        return DistrictResponse.builder()
                .id(district.getId())
                .name(district.getName())
                .build();
    }

}
