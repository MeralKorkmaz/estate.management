package estate.management.com.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import estate.management.com.domain.advert.Advert;
import estate.management.com.domain.category.Category;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourRequestResponse {
    private Long id;
    private LocalDateTime tourDate;
    private LocalDateTime tourTime;
    private int status = 0;
    private int ownerUserId;
    private int guestUserId;
    private Advert advert;
}
