package estate.management.com.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)

@Entity
@Table(name = "favorite")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "user_id", nullable = false)
    @NotNull(message = "Id cannot be null")
    private int userId;


    @Column(name = "advert_id", nullable = false)
    @NotNull(message = "advertId cannot be null")
    private int advertId;



    @Column(name = "create_at", nullable = false)
    @NotNull(message = "Creation cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime createAt;

    @PrePersist
    private void onCreate() {
        createAt = LocalDateTime.now();

    }

}
