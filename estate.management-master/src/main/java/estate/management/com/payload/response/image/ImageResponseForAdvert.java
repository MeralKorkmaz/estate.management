package estate.management.com.payload.response.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ImageResponseForAdvert {

    private Long imageId;
    private String name;
    private String type;
    private boolean featured;
    private byte[] data;

    private Long advertId;
}
