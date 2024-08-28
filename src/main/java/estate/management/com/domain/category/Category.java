
package estate.management.com.domain.category;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import estate.management.com.domain.TourRequest;
import estate.management.com.domain.advert.Advert;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;


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
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "title", nullable = false, length = 150)
    @NotNull(message = "Title cannot be null")
    @Size(max=150 ,message = "Title must be less then {max} characters")
    private String title;

    @Column(name = "icon", nullable = false, length = 50)
    @NotNull(message = "icon cannot be null")
    @Size(max=50 ,message = "icon must be less then {max} characters")
    private String icon;

    @Column(name = "built_in")
    private Boolean built_in=false;

    @Column(name = "seq",nullable = false)
    @NotNull(message = "seq cannot be null")
    private int seq=0;

    @Column(name = "slug", length = 200,nullable = false)
    @NotNull(message = "slug cannot be null")
    @Size(min = 5, max = 200, message = "slug must be between {min} and {max}")
    private String slug;

    @Column(name = "isActive",nullable = false)
    @NotNull(message = "isActive cannot be null")
    private boolean isActive=true;




    @Column(name = "create_at",nullable = false)
    @NotNull(message = "creation cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime createAt;

    @Column(name = "updateAt",nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime updateAt;

    @OneToOne(mappedBy = "category")
    private TourRequest tourRequest;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Advert> adverts;






    @PrePersist
    private void onCreate() {
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        updateAt = LocalDateTime.now();
    }
}
