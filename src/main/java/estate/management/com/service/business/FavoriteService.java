package estate.management.com.service.business;

import estate.management.com.domain.Favorite;
import estate.management.com.domain.advert.Advert;
import estate.management.com.domain.user.User;
import estate.management.com.exception.ResourceNotFoundException;
import estate.management.com.payload.mapper.FavoriteMapper;
import estate.management.com.payload.message.ErrorMessages;
import estate.management.com.payload.message.SuccessMessages;
import estate.management.com.payload.request.FavoriteRequest;
import estate.management.com.payload.response.FavoriteResponse;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.repository.UserRepository;
import estate.management.com.repository.business.AdvertRepository;
import estate.management.com.repository.business.FavoriteRepository;
import lombok.AllArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    private final UserRepository userRepository;

    private final FavoriteMapper favoriteMapper;

    private final AdvertRepository advertRepository;

    public List<FavoriteResponse> getAllFavoriteByUser(HttpServletRequest httpServletRequest) {

        String email = (String) httpServletRequest.getAttribute("email");

        if (!userRepository.existsByEmailEquals(email)){
            throw new UsernameNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE, email));
        }

        User user = userRepository.findByEmailEquals(email);

        // TODO userId User'da Long Favorite'da int type casting yaptım ancak hata verme olasılığı var. Meeting'de sor
        int userId = user.getId().intValue();

         List<FavoriteResponse> responses = favoriteRepository.findAllByUserId(userId).stream().map(
                 favoriteMapper::mapFavoriteToFavoriteResponse
         ).collect(Collectors.toList());

         /* TODO Bu bölüm gerekli olmayabilir. Boşsa exception mı atsın boş list mi getirsin?
         if (responses.isEmpty()){
             throw new ResourceNotFoundException(ErrorMessages.FAVORITE_GENERAL_NOT_FOUND_ERROR);
         };
         */


         return responses;


    }

    public List<FavoriteResponse> getAllFavoritesByAdminAndManager(int userId) {

        Long idOfUser = (long) userId;

        User user = userRepository.findById(idOfUser).orElseThrow(()
                -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE, userId)));

        return favoriteRepository.findAllByUserId(userId).stream().map(
                favoriteMapper::mapFavoriteToFavoriteResponse
        ).collect(Collectors.toList());

    }

    public ResponseMessage<FavoriteResponse> toggleFavorite(int advertId, HttpServletRequest httpServletRequest) {

        Long idOfAdvert = (long) advertId;
        String email = (String) httpServletRequest.getAttribute("email");
        if (!userRepository.existsByEmailEquals(email)) {
            throw new UsernameNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE, email));
        }

        User user = userRepository.findByEmailEquals(email);
        int idOfUser = user.getId().intValue();

        Advert advert = advertRepository.findById(idOfAdvert).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.ADVERT_ID_NOT_FOUND_ERROR, advertId)));

        boolean isFavorite = favoriteRepository.findAllByUserId(idOfUser)
                .stream()
                .anyMatch(favorite -> Objects.equals(favorite.getAdvertId(), advertId));

        if (isFavorite) {

            Favorite favoriteToRemove = favoriteRepository.findByUserIdAndAdvertId(idOfUser, advertId);
            favoriteRepository.delete(favoriteToRemove);

            return ResponseMessage.<FavoriteResponse>builder()
                    .message(SuccessMessages.FAVORITE_REMOVED_SUCCESS)
                    .status(HttpStatus.OK)
                    .build();
        } else {
            // Add new favorite
            Favorite favorite = Favorite.builder()
                    .advertId(advertId)
                    .userId(idOfUser)
                    .build();

            Favorite addedFavorite = favoriteRepository.save(favorite);

            return ResponseMessage.<FavoriteResponse>builder()
                    .message(SuccessMessages.FAVORITE_ADDED_SUCCESS)
                    .object(favoriteMapper.mapFavoriteToFavoriteResponse(addedFavorite))
                    .status(HttpStatus.CREATED)
                    .build();
        }
    }

    public ResponseMessage deleteAllFavorites(HttpServletRequest httpServletRequest) {

        String email = (String)httpServletRequest.getAttribute("email");
        User user = userRepository.findByEmailEquals(email);

        List<Favorite> favorites = favoriteRepository.findAllByUserId(user.getId().intValue());

        favoriteRepository.deleteAll(favorites);

        return ResponseMessage.builder()
                .message(SuccessMessages.FAVORITE_REMOVED_SUCCESS)
                .status(HttpStatus.OK)
                .build();
    }

    public ResponseMessage deleteAllFavoritesByAdminAndManager(int userId) {

        Long idOfUser = (long) userId;

        User user = userRepository.findById(idOfUser).orElseThrow(()
                -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE, userId)));

        List<Favorite> favorites = favoriteRepository.findAllByUserId(userId);

        favoriteRepository.deleteAll(favorites);

        return ResponseMessage.builder()
                .message(SuccessMessages.FAVORITE_REMOVED_SUCCESS)
                .status(HttpStatus.OK)
                .build();



    }

    public ResponseMessage<String> deleteFavoriteByAdminAndManager(Long favoriteId) {

        Favorite favoriteToDelete = favoriteRepository.findById(favoriteId).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.FAVORITE_ID_NOT_FOUND_ERROR, favoriteId))
        );

        favoriteRepository.delete(favoriteToDelete);

        return ResponseMessage.<String>builder()
                .status(HttpStatus.OK)
                .message(SuccessMessages.FAVORITE_REMOVED_SUCCESS)
                .build();

    }
}
