package estate.management.com.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.GroupSequence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)


@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name ="first_name", nullable = false )
    @Size(max = 30, message = "firstName cannot be less than {max}")
    @NotNull(message = "firstName cannot be null")
    private String firstName;

    @Column(name ="last_name", nullable = false )
    @Size(max = 30, message = "lastName cannot be less than {max}")
    @NotNull(message = "lastName cannot be null")
    private String lastName;

    @Column(name ="email", nullable = false )
    @Size(max = 60, message = "eMail cannot be less than {max}")
    @NotNull(message = "eMail cannot be null")
    @Email
    private String eMail;

    @Column(name ="message", nullable = false )
    @Size(max = 300, message = "firstName cannot be less than {max}")
    @NotNull(message = "firstName cannot be null")
    private String message;

    @Column(name ="status", nullable = false )
    @NotNull(message = "firstName cannot be null")
    private int status =0;

    @Column(name = "create_at", nullable = false)
    @NotNull(message = "Creation cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime createAt;

    @PrePersist
    private void createAt() {
        createAt = LocalDateTime.now();

    }
}
