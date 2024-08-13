package estate.management.com.payload.response.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private Long id;
    private String title;
    private String icon;
    private int seq;
    private String slug;
    private boolean isActive;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;



}
