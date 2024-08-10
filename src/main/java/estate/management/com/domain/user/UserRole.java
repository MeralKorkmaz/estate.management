package estate.management.com.domain.user;


//TODO is it entity since there is not PK in the documentation
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)

@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    //bunu anlamadim

   @OneToOne
    private User user;

    private String roleName;

    @Enumerated(EnumType.STRING)
    private RoleName roleType;
}

