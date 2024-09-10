
package estate.management.com.payload.request.advert;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class AdvertRequestForCity {

    @Column(name = "city_id", nullable = false)
    @NotNull(message = "cityId cannot be null" )
    private int cityId;

}
