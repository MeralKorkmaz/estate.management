package estate.management.com.payload.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertTypeRequest {

    @NotNull(message = "Title cannot be empty.")
    @Size(max = 30, message = "Title must be less than {max} characters.")
    private String title;

}
