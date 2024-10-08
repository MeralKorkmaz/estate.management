package estate.management.com.domain.administrative;


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
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //changed form AUTO to IDENTITY
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name="country_name", nullable = false, length= 30)
    @NotNull(message = "Country cannot be null")
    @Size(max = 30, message = "Country must be less than {max}")
    private String name;
}
