
package estate.management.com.domain.advert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import estate.management.com.domain.Image;
import estate.management.com.domain.TourRequest;
import estate.management.com.domain.administrative.City;
import estate.management.com.domain.category.Category;
import estate.management.com.domain.category.CategoryPropertyKey;
import estate.management.com.domain.category.CategoryPropertyValue;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;


@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter


@Entity
@Table(name = "Advert")
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "title", nullable = false, length = 150)
    @NotNull(message = "Title cannot be null")
    @Size(min = 5, max = 150, message = "Title must be between {min} and {max} characters")
    private String title;

    // FIX: desc is a reserved Keyword in PostgreSQL. Changed to description
    @Column(name = "description", length = 300)
    @Size(max = 300, message = "Description can contain maximum {max} characters")
    private String description;


    //TODO: slug arastirilacak
    @Column(name = "slug", nullable = false, length = 200)
    @NotNull(message = "Slug cannot be null")
    @Size(min = 5, max = 200,  message = "Slug must be between {min} and {max} characters")
    private String slug;



    @Column(name = "price", nullable = false)
    @NotNull(message = "Price cannot be null")
    private Double price;

    @Column(name = "status", nullable = false)
    @NotNull(message = "Status cannot be null, default 0")
    private int status = 0;

    // Have an issue here, documentation given type should be boolean but default value shoudl be 0.
    @Column(name = "built_in", nullable = false)
    @NotNull(message = "Cannot be null, default false")
    private Boolean built_in= false;

    //TODO is active means it's published or still selling the item.
    @Column(name = "is_active", nullable = false)
    @NotNull(message = "Cannot be null")
    private boolean isActive = true;

    @Column(name = "view_count", nullable = false)
    @NotNull(message = "Cannot be null, default 0")
    private int viewCount = 0;

    // Google embeded code should be stored in this field.
    @Column(name = "location", nullable = false, columnDefinition = "TEXT")
    @NotNull(message = "Location cannot be null")
    private String location;

    @Column(name = "create_at", nullable = false)
    @NotNull(message = "Creation cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime updateAt;


    // FOREIGN KEY FIELDS

    @Column(name = "advert_type_id", nullable = false)
    @NotNull(message = "advertTypeId cannot be null" )
    private int advertTypeId;

    @Column(name = "country_id", nullable = false)
    @NotNull(message = "countryId cannot be null" )
    private int countryId;

    @ManyToOne
    @JoinColumn(name = "city_id")
   private City city;

    @Column(name = "discrict_id", nullable = false)
    @NotNull(message = "disctrictId cannot be null" )
    private int districtId;

    @Column(name = "user_id", nullable = false)
    @NotNull(message = "userId cannot be null" )
    private int userId;


    @OneToMany(mappedBy = "advert")
    @JsonIgnore
    private List<CategoryPropertyValue> categoryPropertyValues;

    @OneToMany(mappedBy = "advert")
    @JsonIgnore
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "advert")
    @JsonIgnore
    private List<TourRequest> tourRequests;







    // ------ METHODS --------


    /*
    Method to generate and prePersist/preUpdate slug field from title automatically.
    Creation time prePersist to current time if not set manually.
    PreUpdate updatedAt field.
    */
//    @PrePersist
//    @PreUpdate
//    public void prePersistEntity() {
//        this.slug = toSlug(this.title);
//        if (this.createAt == null) {
//            this.createAt = LocalDateTime.now();
//        }
//        this.updateAt = LocalDateTime.now();
    //}


    @PrePersist
    private void createAt() {
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }

    @PreUpdate
    private void updateAt() {
        updateAt = LocalDateTime.now();
    }

}
