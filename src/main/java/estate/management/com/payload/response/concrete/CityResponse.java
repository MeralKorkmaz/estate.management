package estate.management.com.payload.response.concrete;

import lombok.*;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityResponse {

    private String city;
    private long amount;  // Field to hold the count

}
