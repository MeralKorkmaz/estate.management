package estate.management.com.domain.category;

import com.fasterxml.jackson.annotation.JsonFormat;

import estate.management.com.domain.TourRequest;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private boolean isActive;




    @Column(name = "create_at",nullable = false)
    @NotNull(message = "creation cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime createAt;

    @Column(name = "updateAt",nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime updateAt;

    @OneToOne(mappedBy = "category")
    private TourRequest tourRequest;
    @ElementCollection
    @CollectionTable(name = "category_properties", joinColumns = @JoinColumn(name = "category_id"))
    @Column(name = "property")
    private List<String> properties = new ArrayList<>();
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryPropertyKey> propertyKeys = new ArrayList<>();


    @PrePersist
    private void onCreate() {
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        updateAt = LocalDateTime.now();
    }
    public void setProperties(List<String> properties) {
        this.properties = properties;
    }
    public List<String> getProperties() {
        return properties;
    }


    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setPropertyKeys(List<CategoryPropertyKey> propertyKeys) {
        this.propertyKeys = propertyKeys;
    }



    // Method to get the name from CategoryPropertyKey
    public String getName() {
        return propertyKeys.stream()
                .map(CategoryPropertyKey::getName)
                .findFirst()
                .orElse(null);
    }

    // Method to get the value from CategoryPropertyKey
    public String getValue() {
        return propertyKeys.stream()
                .map(CategoryPropertyKey::getValue)
                .findFirst()
                .orElse(null);
    }
}


