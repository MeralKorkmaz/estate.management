package estate.management.com.repository.business;

import estate.management.com.domain.advert.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {

    boolean existsByCategoryId(Long id);//I need this code for category service class
}
