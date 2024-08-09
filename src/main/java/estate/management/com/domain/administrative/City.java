package estate.management.com.domain.administrative;

import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    @Size(max = 30, message = "name must be less than {max}" )
    private String name;

    @Column(name = "country_id",nullable = false)
    @NotNull(message = "countryId cannot be null")
    private int countryId;


}