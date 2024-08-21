package estate.management.com.repository.business;

import estate.management.com.domain.TourRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TourRequestRepository extends JpaRepository<TourRequest, Long> {
    //Method to return the number of TourRequests
    long count();
}
