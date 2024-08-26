package estate.management.com.payload.request.advert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyRequest {

    @NotNull(message = "Key ID cannot be null")
    private Long keyId;

    @NotNull(message = "Value cannot be null")
    private String value;
}
