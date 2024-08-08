package estate.management.com.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;

    @Column(name = "value", nullable = false, length = 100)
    @NotNull(message = "value cannot be null")
    @Size( max = 100, message = "value must be less then {max} characters")
    private String value;



    @Column(name = "advert_id")
    private int advertId;


    @Column(name = "category_property_key_id")
    private int categoryPropertyKeyId;

}
