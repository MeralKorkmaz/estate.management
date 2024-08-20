package estate.management.com.payload.request;
import estate.management.com.domain.category.CategoryPropertyKey;
import jdk.jfr.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPropertyValueRequest {
    private String value;
    private int advertId;



}