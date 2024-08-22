package estate.management.com.payload.mapper;

import estate.management.com.domain.advert.AdvertType;
import estate.management.com.payload.request.AdvertTypeRequest;
import estate.management.com.payload.response.AdvertTypeResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AdvertTypeMapper {

    public AdvertType mapAdvertTypeRequestToAdvertType(AdvertTypeRequest advertTypeRequest) {
        return AdvertType.builder()
                .title(advertTypeRequest.getTitle())
                .build();
    }

    public AdvertTypeResponse mapAdvertTypeToAdvertTypeResponse(AdvertType advertType) {
        return AdvertTypeResponse.builder()
                .id(advertType.getId())
                .title(advertType.getTitle())
                .built_in(advertType.getBuilt_in())
                .build();
    }

}
