package estate.management.com.repository.business;

import estate.management.com.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findAllByUserId(int userId);

    Favorite findByUserIdAndAdvertId(int idOfUser, int advertId);
}