package estate.management.com.payload.response.business;
import com.fasterxml.jackson.annotation.JsonInclude;
import estate.management.com.domain.TourRequest;
import estate.management.com.domain.category.CategoryPropertyKey;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String icon;
    private Boolean built_in;
    private int seq = 0;
    private String slug;
    private boolean isActive;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private List<CategoryPropertyKeyResponse> categoryPropertyKey;}