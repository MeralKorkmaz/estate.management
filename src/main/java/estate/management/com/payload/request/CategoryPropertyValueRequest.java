package estate.management.com.payload.request;
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
    private Long advertId;
    private Long categoryPropertyKey;


    public String getValue() { return value;
    }

    public Long getAdvertId() {return advertId;
    }

    public Long getId() {return id;
    }
}