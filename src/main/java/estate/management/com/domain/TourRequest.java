package estate.management.com.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import estate.management.com.domain.advert.Advert;
import estate.management.com.domain.category.Category;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter

@Entity
@Table(name = "tour_request")
public class TourRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "tour_date", nullable = false)
    @NotNull(message = "The tour date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime tourDate;

    @Column(name = "tour_time", nullable = false)
    @NotNull(message = "The tourTime cannot be null")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalDateTime tourTime;

    @Column(name ="status",nullable = false)
    @NotNull(message = "status cannot be null")
    private int status = 0;






    @Column(name = "owner_user_id", nullable = false)
    @NotNull(message = "ownerUserId cannot be null")
    private int ownerUserId;


    @Column(name = "guest_user_id", nullable = false)
    @NotNull(message = "guestUserId cannot be null")
    private int guestUserId;

    @Column(name = "create_at", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @NotNull(message = "createAt cant be null")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime updateAt;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "tour_requests_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "advert_id")
    private Advert advert;


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
