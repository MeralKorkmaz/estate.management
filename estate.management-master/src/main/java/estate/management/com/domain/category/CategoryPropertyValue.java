
package estate.management.com.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import estate.management.com.domain.advert.Advert;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter

@Entity
@Table(name = "category_property_value")
public class CategoryPropertyValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "value", nullable = false, length = 100)
    @NotNull(message = "value cannot be null")
    @Size( max = 100, message = "value must be less then {max} characters")
    private String value;



    @ManyToOne
    @JoinColumn(name = "advert_id")
    private Advert advert;


    @ManyToOne
    @JoinColumn(name = "category_property_key_id")
    private CategoryPropertyKey categoryPropertyKey;

}