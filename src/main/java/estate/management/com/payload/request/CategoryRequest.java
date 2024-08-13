package estate.management.com.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {
    private Long id;
    private String title;
    private String icon;
    private int seq;
    private String slug;
    private boolean isActive;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;




}

