package estate.management.com.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {
    private Long id;
    private String title;
    private String icon;
    private int seq=0;
    private String slug;
    private boolean isActive=true;
}

