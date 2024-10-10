package estate.management.com.repository.business;

import estate.management.com.domain.administrative.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
