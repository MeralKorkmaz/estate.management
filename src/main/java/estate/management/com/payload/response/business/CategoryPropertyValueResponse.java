package estate.management.com.payload.response.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import estate.management.com.domain.category.CategoryPropertyKey;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CategoryPropertyValueResponse {
    private Long id;
    private String value;
    private int advertId;
}