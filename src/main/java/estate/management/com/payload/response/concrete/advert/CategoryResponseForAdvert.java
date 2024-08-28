package estate.management.com.payload.response.concrete.advert;

import jdk.jfr.Registered;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponseForAdvert {

    private String category;
    private long amount;
}
