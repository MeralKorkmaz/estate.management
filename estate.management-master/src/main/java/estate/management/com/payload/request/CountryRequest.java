package estate.management.com.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryRequest {

    @NotNull(message = "Name cannot be null.")
    @Size(max = 30, message = "Length must be less than {max}.")
    private String name;

}
