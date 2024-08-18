package estate.management.com.payload.request;
import estate.management.com.domain.category.CategoryPropertyKey;
import jdk.jfr.Category;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPropertyValueRequest {
    private Long id;
    private String value;
    private int advertId;
    private CategoryPropertyKey categoryPropertyKey;



}