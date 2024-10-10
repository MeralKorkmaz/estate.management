
package estate.management.com.controller.business;

import estate.management.com.payload.response.FavoriteResponse;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.service.business.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;


    // http://localhost:8080/favorites/auth
    @GetMapping("/auth")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<List<FavoriteResponse>> getAllFavoriteByUser(HttpServletRequest httpServletRequest){
        return ResponseEntity.ok(favoriteService.getAllFavoriteByUser(httpServletRequest));
    }


    // http://localhost:8080/favorites/admin/1
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/admin/{userId}")
    public ResponseEntity<List<FavoriteResponse>> getAllFavoritesByAdminAndManager(@PathVariable int userId){
        return ResponseEntity.ok(favoriteService.getAllFavoritesByAdminAndManager(userId));
    }

    // http://localhost:8080/favorites/1/auth
    @PostMapping("/{advertId}/auth")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseMessage<FavoriteResponse> toggleFavorite(@PathVariable int advertId,
                                                            HttpServletRequest httpServletRequest){
        return favoriteService.toggleFavorite(advertId ,httpServletRequest);
    }

    // http://localhost:8080/favorites/auth
    @DeleteMapping("/auth")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseMessage<String> deleteAllFavorites(HttpServletRequest httpServletRequest){

        return favoriteService.deleteAllFavorites(httpServletRequest);
    }

    // http://localhost:8080/favorites/admin/1
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @DeleteMapping("/admin/{userId}")
    public ResponseMessage<String> deleteAllFavoritesByAdminAndManager(@PathVariable int userId){
        return favoriteService.deleteAllFavoritesByAdminAndManager(userId);
    }

    //http://localhost:8080/favorites/1/admin
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @DeleteMapping("/{favoriteId}/admin")
    public ResponseMessage<String> deleteFavoriteByAdminAndManager(@PathVariable Long favoriteId){
        return favoriteService.deleteFavoriteByAdminAndManager(favoriteId);
    }




}
