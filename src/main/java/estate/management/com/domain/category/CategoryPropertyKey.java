package estate.management.com.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@Entity
@Table(name = "category_property_keys")
public class CategoryPropertyKey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false, length = 80)
    @NotNull(message = "name cannot be null")
    @Size(min = 2, max = 80, message = "Title must be between {min} and {max} characters")
    private String name;

    @Column(name = "built_in", nullable = false, updatable = false)
    private Boolean built_in = false;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    @JsonIgnore
    private Category category;
    @JsonIgnore
    @OneToMany(mappedBy = "categoryPropertyKey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryPropertyValue> categoryPropertyValue = new ArrayList<>();

    public List<CategoryPropertyValue> getCategoryPropertyValue() {
        return categoryPropertyValue;
    }



//    public void addCategoryPropertyValue(CategoryPropertyValue propertyValues) {
//        propertyValues.setCategoryPropertyKey(this);
//        categoryPropertyValue.add(propertyValues);
//    }


}










