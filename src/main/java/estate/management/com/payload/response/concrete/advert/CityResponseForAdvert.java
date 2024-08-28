package estate.management.com.payload.response.concrete.advert;

import lombok.*;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityResponseForAdvert {

    private String city;
    private long amount;  // Field to hold the count

}
