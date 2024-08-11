package estate.management.com.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @Column(name = "built_in")
    private Boolean built_in = false;


    @Column(name = "category_id")
    private int categoryId;
}




