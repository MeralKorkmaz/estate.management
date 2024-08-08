package estate.management.com.domain.user;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import lombok.*;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstName", nullable = false, length = 30)
    @Size(min = 2, max = 30, message = "firstName must be between {min} and {max} characters")
    @NotNull(message = "firstName cannot be null")
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 30)
    @Size(min = 2, max = 30, message = "lastName must be between {min} and {max}  characters")
    @NotNull(message = "lastName cannot be null")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 80)
    @Size(min = 10, max = 80, message = "email must be between {min} and {max} characters")
    @NotNull(message = "email cannot be null")
    @Email
    private String email;

    @Column(name = "phone", nullable = false)
    @NotNull(message = "phone cannot be null")
    private String phone;


    @Column(name = "password_hash", nullable = false)
    @NotNull(message = "password cannot be null")
    private String passwordHash;


    @Column(name = "reset_password_code", nullable = true)
    private String resetPasswordCode;

    @Column(name = "built_in")
    private Boolean built_in = false;

    @Column(name = "create_at", nullable = false)
    @NotNull(message = "Create cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime updateAt;


    @OneToMany(mappedBy = "user")
    private Set<UserRole> userRoles;



    @PrePersist
    private void onCreate() {
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        updateAt = LocalDateTime.now();
    }
}
