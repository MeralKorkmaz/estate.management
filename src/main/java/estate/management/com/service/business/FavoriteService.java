package estate.management.com.service.business;

import estate.management.com.domain.Favorite;
import estate.management.com.repository.business.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service
@RequiredArgsConstructor
public class FavoriteService {

    /*

    private final FavoriteRepository favoriteRepository;


    //    TODO add logic (get fav id and map it to the user and related advert)
    public List<Favorite> getAllFavoritesByUser(HttpServletRequest httpServletRequest) {

        return null;
    }

    public List<Favorite> getUserFavoriteByUserId(Long id) {

        // TODO check whether user is valid

        // TODO add mapper for userId favorite mapping
        return null;

    }


    public Favorite toggleFavoriteByUser(Long advertId) {
        // TODO add non-exsiting adverts, while remove existing adverts.
        return null;
    }


    // TODO DO we need more logic? A question for ensure or etc?
    public void deleteAllUserFavorites(HttpServletRequest httpServletRequest) {
     }

     */
}
