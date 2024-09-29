package estate.management.com.payload.request.Image;

import estate.management.com.domain.advert.Advert;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageRequestForAdvert {

    private String name;
    private String type;
    private boolean featured;
    private byte[] data;
    private Long advertId;
}
