package estate.management.com.payload.request.Image;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ImageRequest {



    @NotNull(message = "Image name cannot be null")
    private String name;

    @NotNull(message = "Image data cannot be null")
    private byte[] data;

    private String type;

    @NotNull(message = "Featured status cannot be null")
    private boolean featured;
}
