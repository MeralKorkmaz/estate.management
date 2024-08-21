package estate.management.com.repository.business;

import estate.management.com.domain.advert.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {

    // Method to check if an advert exist in an advertType.
    boolean existsByAdvertTypeId(Long advertTypeId);

    // Method returns number of Adverts
    long countByIsActive(boolean isActive);
}
