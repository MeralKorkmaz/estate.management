package estate.management.com.payload.response.business;
import com.fasterxml.jackson.annotation.JsonInclude;
import estate.management.com.domain.category.CategoryPropertyKey;
import estate.management.com.payload.response.abstracts.BaseCategoryResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Getter
@Setter
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponse extends BaseCategoryResponse {
    private List<CategoryPropertyKey> categoryPropertyKey;
}