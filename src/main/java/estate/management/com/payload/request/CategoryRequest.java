package estate.management.com.payload.request;

import estate.management.com.domain.category.CategoryPropertyValue;
import estate.management.com.payload.response.business.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

@Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class CategoryRequest {
        private Long id;
    @NotNull(message = "Title must not be empty")
        private String title;
        @NotNull(message = "Icon is required")
        private String icon;
        private Boolean built_in = false;
    @NotNull(message = "Seq must not be empty")
        private int seq;
        @NotNull(message = "Slug is required")
        @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
        private String slug;
        private boolean active = true;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Value cannot be null")
    private String value;

        public boolean getActive() {
            return active;
        }


    }



