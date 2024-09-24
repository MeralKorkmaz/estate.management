package estate.management.com.repository.business;

import estate.management.com.domain.TourRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRequestRepository extends JpaRepository<TourRequest,Long> {


    @Query(value = "SELECT t.* FROM tour_request t WHERE t.guest_user_id = :userId " +
            "AND (:q IS NULL OR " +
            "CAST(t.status AS text) LIKE CONCAT('%', :q, '%') OR " +
            "CAST(t.tour_date AS text) LIKE CONCAT('%', :q, '%') OR " +
            "LOWER(CAST(t.advert AS text)) LIKE LOWER(CONCAT('%', :q, '%')) OR " +
            "LOWER(CAST(t.category AS text)) LIKE LOWER(CONCAT('%', :q, '%')))",
            nativeQuery = true)
    Page<TourRequest> searchUserTourRequests(@Param("q") String query,
                                             @Param("userId") Long userId,
                                             Pageable pageable);



    @Query("SELECT t FROM TourRequest t WHERE " +
            "(:q IS NULL OR " +
            "CAST(t.status AS string) LIKE CONCAT('%', :q, '%') OR " +
            "CAST(t.tourDate AS string) LIKE CONCAT('%', :q, '%') OR " +
            "CAST(t.ownerUserId AS string) LIKE CONCAT('%', :q, '%') OR " +
            "CAST(t.guestUserId AS string) LIKE CONCAT('%', :q, '%'))")
    Page<TourRequest> getAllTourRequestsByAdmin(@Param("q") String query, Pageable pageable);

    List<TourRequest> findByGuestUserId(Long userId);
}


