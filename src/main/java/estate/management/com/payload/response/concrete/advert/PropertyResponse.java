package estate.management.com.payload.response.concrete.advert;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyResponse {

    private Long keyId;
    private String value;
}
