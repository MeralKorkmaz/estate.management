package estate.management.com.repository.business;

import estate.management.com.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {


    // TODO there is not a username check it out again!
    //void deleteByUsername(String username);
}
