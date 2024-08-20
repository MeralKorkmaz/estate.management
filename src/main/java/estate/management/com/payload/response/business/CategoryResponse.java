package estate.management.com.payload.response.business;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import estate.management.com.domain.TourRequest;
import estate.management.com.domain.category.CategoryPropertyKey;
import estate.management.com.payload.response.abstracts.BaseCategoryResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private Boolean built_in = false;
    private int seq = 0;
    private String slug;
    private boolean isActive = true;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private TourRequest tourRequest;
    private List<CategoryPropertyKey> categoryPropertyKey;
}