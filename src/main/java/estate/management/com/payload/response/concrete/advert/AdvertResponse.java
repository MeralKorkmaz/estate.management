
package estate.management.com.payload.response.concrete.advert;

import estate.management.com.payload.request.ImageRequest;
import estate.management.com.payload.request.advert.PropertyRequest;
import estate.management.com.payload.response.ImageResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AdvertResponse {


    private Long id;
    private String title;
    private String description;
    private Double price;
    private int advertTypeId;
    private int countryId;
    private int cityId;
    private int districtId;
    private String location;
    private boolean isActive;
    private int viewCount;
    private List<ImageResponse> images;
    private List<PropertyResponse> properties;
    private String slug;


}
