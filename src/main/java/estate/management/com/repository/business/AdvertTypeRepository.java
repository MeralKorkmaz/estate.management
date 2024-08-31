package estate.management.com.repository.business;

import estate.management.com.domain.advert.AdvertType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdvertTypeRepository extends JpaRepository<AdvertType, Long> {

    //Methode returns number of AdvertTypes
    long count();

}
