//package estate.management.com.payload.request;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.*;
//
//import javax.persistence.Column;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.time.LocalDateTime;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder(toBuilder = true)
//public class AdvertRequest {
//
//    @Column(name = "title", nullable = false, length = 150)
//    @NotNull(message = "Title cannot be null")
//    @Size(min = 5, max = 150, message = "Title must be between {min} and {max} characters")
//    private String title;
//
//    @Column(name = "description", length = 300)
//    @Size(max = 300, message = "Description can contain maximum {max} characters")
//    private String description;
//
//    @Column(name = "slug", nullable = false, length = 200)
//    @NotNull(message = "Slug cannot be null")
//    @Size(min = 5, max = 200,  message = "Slug must be between {min} and {max} characters")
//    private String slug;
//
//
//    @Column(name = "price", nullable = false)
//    @NotNull(message = "Price cannot be null")
//    private Double price;
//
//    @Column(name = "status", nullable = false)
//    @NotNull(message = "Status cannot be null, default 0")
//    private int status = 0;
//
//    // Have an issue here, documentation given type should be boolean but default value shoudl be 0.
//    @Column(name = "built_in", nullable = false)
//    @NotNull(message = "Cannot be null, default false")
//    private Boolean built_in= false;
//
//    //TODO is active means it's published or still selling the item.
//    @Column(name = "is_active", nullable = false)
//    @NotNull(message = "Cannot be null")
//    private boolean isActive = true;
//
//    @Column(name = "view_count", nullable = false)
//    @NotNull(message = "Cannot be null, default 0")
//    private int viewCount = 0;
//
//    // Google embeded code should be stored in this field.
//    @Column(name = "location", nullable = false)
//    @NotNull(message = "Location cannot be null")
//    private String location;
//
//
//    // FOREIGN KEY FIELDS
//
//    @Column(name = "advert_type_id", nullable = false)
//    @NotNull(message = "advertTypeId cannot be null" )
//    private int advertTypeId;
//
//    @Column(name = "country_id", nullable = false)
//    @NotNull(message = "countryId cannot be null" )
//    private int countryId;
//
//    @Column(name = "city_id", nullable = false)
//    @NotNull(message = "cityId cannot be null" )
//    private int cityId;
//
//    @Column(name = "discrict_id", nullable = false)
//    @NotNull(message = "disctrictId cannot be null" )
//    private int districtId;
//
//    @Column(name = "user_id", nullable = false)
//    @NotNull(message = "userId cannot be null" )
//    private int userId;
//
//    @Column(name = "category_id", nullable = false)
//    @NotNull(message = "categoryId cannot be null" )
//    private int categoryId;
//
//
//
//
//
//
//
//
//
//}
