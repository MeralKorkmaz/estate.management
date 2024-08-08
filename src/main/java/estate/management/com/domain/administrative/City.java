package estate.management.com.domain.administrative;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    @Size(max = 30, message = "name must be less than {max}" )
    private String name;

    @Column(name = "country_id",nullable = false)
    @NotNull(message = "countryId cannot be null")
    private int countryId;


}