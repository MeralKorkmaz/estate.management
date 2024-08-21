package estate.management.com.payload.response.business;
import com.fasterxml.jackson.annotation.JsonInclude;
import estate.management.com.domain.category.Category;
import estate.management.com.domain.category.CategoryPropertyValue;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CategoryPropertyKeyResponse {
    private Long id;
    private String name;
    private Boolean built_in;
    private Category category;
    private List<CategoryPropertyValue> categoryPropertyValue;}