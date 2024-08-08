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
//TODO why it says cannot resolve table user_roles and column user_roles_id
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //bunu anlamadim

    @Column(name = "user_roles_id")
    private int user;
}

