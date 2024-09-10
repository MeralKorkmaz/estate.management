package estate.management.com.payload.request;
import estate.management.com.domain.advert.Advert;
import lombok.*;
import javax.validation.constraints.NotNull;
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPropertyValueRequest {
    private Long id;
    @NotNull
    private String value;
    @NotNull
    private Advert advert;

    private Long categoryPropertyKey;


    public String getValue() { return value;
    }

    public Advert getAdvert() {return advert;
    }

    public Long getId() {return id;
    }
}