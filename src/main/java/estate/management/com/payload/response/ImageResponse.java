package estate.management.com.payload.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {

    private String name;
    private byte[] data;
    private String type;
    private boolean featured;
}
