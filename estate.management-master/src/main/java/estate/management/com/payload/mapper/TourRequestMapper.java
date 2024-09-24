package estate.management.com.payload.mapper;

import estate.management.com.domain.TourRequest;
import estate.management.com.payload.request.TourRequestRequestDto;
import estate.management.com.payload.response.TourRequestResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TourRequestMapper {



    public TourRequestResponse mapTourRequestToTourRequestResponse(TourRequest tourRequest){
        TourRequestResponse tourRequestResponse = TourRequestResponse.builder()
                .tourTime(tourRequest.getTourTime())
                .tourDate(tourRequest.getTourDate())
                .id(tourRequest.getId())
                .guestUserId(tourRequest.getGuestUserId())
                .ownerUserId(tourRequest.getOwnerUserId())
                .advert(tourRequest.getAdvert())
                .status(tourRequest.getStatus())
                .build();
        return tourRequestResponse;
    }






}
