
package estate.management.com.payload.request.advert;

import estate.management.com.payload.request.Image.ImageRequest;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AdvertRequest {
    @NotNull(message = "Title cannot be null")
    @Size(min = 5, max = 150, message = "Title must be between {min} and {max} characters")
    private String title;

    @NotNull(message = "Description cannot be null")
    @Size(max = 300, message = "Description can contain a maximum of {max} characters")
    private String description;

    @NotNull(message = "Price cannot be null")
    private Double price;

    @NotNull(message = "Advert Type ID cannot be null")
    private int advertTypeId;

    @NotNull(message = "Country ID cannot be null")
    private int countryId;

    @NotNull(message = "City ID cannot be null")
    private int cityId;

    @NotNull(message = "District ID cannot be null")
    private int districtId;

    @NotNull(message = "Location cannot be null")
    private String location;

    @NotNull(message = "Images cannot be null")
    private List<ImageRequest> images;

    @NotNull(message = "Properties cannot be null")
    private List<PropertyRequest> properties;

    @Column(name = "slug", nullable = false, length = 200)
    @NotNull(message = "Slug cannot be null")
    @Size(min = 5, max = 200,  message = "Slug must be between {min} and {max} characters")
    private String slug;

    @Column(name = "view_count", nullable = false)
    @NotNull(message = "Cannot be null, default 0")
    private int viewCount = 10;

}