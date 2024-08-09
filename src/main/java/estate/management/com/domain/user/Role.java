package estate.management.com.domain.user;

import jakarta.persistence.*;

import lombok.*;

import jakarta.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)

@Entity
@Table(name="roles")

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "roleName cannot be null")
    private RoleName roleName;

}
