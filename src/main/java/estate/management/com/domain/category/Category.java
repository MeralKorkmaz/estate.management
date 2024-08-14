package estate.management.com.domain.category;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import estate.management.com.domain.TourRequest;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;



    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(toBuilder = true)
    @Getter
    @Setter
    @Entity
    @Table(name = "category")
    public class Category {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @Column(name = "title", nullable = false, length = 150)
        @NotNull(message = "Title cannot be null")
        @Size(max=150 ,message = "Title must be less then {max} characters")
        private String title;
        @NotNull(message = "Icon is required")
        @Column(name = "icon", nullable = false, length = 50)
        @Size(max=50 ,message = "icon must be less then {max} characters")
        private String icon;
        @Column(name = "built_in", nullable = false, updatable = false)
        private Boolean built_in=false;
        @Column(name = "seq",nullable = false)
        @NotNull(message = "seq cannot be null")
        private int seq=0;
        @Size(min = 5, max = 200, message = "Slug must be between 5 and 200 characters")
        @Pattern(regexp = "^[a-zA-Z0-9-_]+$")
        @Column(name = "slug",nullable = false)
        private String slug;
        @Column(name = "isActive",nullable = false)
        @NotNull(message = "isActive cannot be null")
        private boolean active=true;
        @Column(name = "create_at",nullable = false)
        @NotNull(message = "creation cannot be null")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime createAt;
        @Column(name = "updateAt",nullable = true)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime updateAt;
        @OneToOne(mappedBy = "category")
        private TourRequest tourRequest;
        @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
        private Set<CategoryPropertyKey> categoryPropertyKey;
        @PrePersist
        private void onCreate() {
            createAt = LocalDateTime.now();
            updateAt = LocalDateTime.now();
        }
        @PreUpdate
        private void onUpdate() {
            updateAt = LocalDateTime.now();
        }
        public boolean getActive() {
            return active;
        }



}


