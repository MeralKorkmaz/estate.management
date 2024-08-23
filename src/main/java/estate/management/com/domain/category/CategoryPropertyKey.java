package estate.management.com.domain.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Column(name = "built_in", nullable = false)
    private Boolean built_in = false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    @JsonIgnore
    private Category category;
    @OneToMany(mappedBy = "categoryPropertyKey",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<CategoryPropertyValue> categoryPropertyValue=new ArrayList<>();

}










