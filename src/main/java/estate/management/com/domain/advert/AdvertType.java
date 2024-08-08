package estate.management.com.domain.advert;

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
@Table(name = "advert_type")
public class AdvertType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false, length = 30)
    @NotNull(message = "Title cannot be null")
    @Size(max = 30, message = "Title must be less then {max} characters")
    private String title;

    @Column(name = "built_in")
    private Boolean built_in = false;

}
