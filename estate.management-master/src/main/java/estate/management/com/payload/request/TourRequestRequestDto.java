package estate.management.com.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import estate.management.com.domain.advert.Advert;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourRequestRequestDto {

    @NotNull(message = "The tour date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime tourDate;


    @NotNull(message = "The tourTime cannot be null")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalDateTime tourTime;

    @NotNull(message = "The advert_id cannot be null")
    private int advert_id;

}
