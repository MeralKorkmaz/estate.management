package estate.management.com.payload.mapper;

import estate.management.com.domain.Favorite;
import estate.management.com.payload.request.FavoriteRequest;
import estate.management.com.payload.response.FavoriteResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FavoriteMapper {

    public FavoriteResponse mapFavoriteToFavoriteResponse(Favorite favorite){

        return FavoriteResponse.builder()
                .id(favorite.getId())
                .userId(favorite.getUserId())
                .advertId(favorite.getAdvertId())
                .build();

    }

    public FavoriteResponse mapFavoriteRequestToFavorite(FavoriteRequest favoriteRequest){

        return FavoriteResponse.builder()
                .userId(favoriteRequest.getUserId())
                .advertId(favoriteRequest.getAdvertId())
                .build();

    }

}
