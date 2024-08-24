package estate.management.com.controller.business;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    /*
    private final FavoriteService favoriteService;

    // TODO create FavoriteResponse and map it!!!
    @PreAuthorize("hasAuthority('Customer')")
    @GetMapping("/auth")
    public ResponseEntity<List<Favorite>> getAllFavoritesByUser(HttpServletRequest httpServletRequest){

        return  ResponseEntity.ok(favoriteService.getAllFavoritesByUser(httpServletRequest));
    }

    // hasAuthority changed as following
    @PreAuthorize("hasAnyAuthority('Admin', 'Manager')")
    @GetMapping("/admin/{userId}")
    public ResponseEntity<List<Favorite>> getUserFavoriteByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(favoriteService.getUserFavoriteByUserId(userId));
    }

    @PreAuthorize("hasAuthority('Customer')")
    @PostMapping("{advertId}/auth")
    public ResponseEntity<Favorite> toggleFavoriteByUser(@PathVariable Long advertId){
        return ResponseEntity.ok(favoriteService.toggleFavoriteByUser(advertId));
    }

    @PreAuthorize("hasAuthority('Customer')")
    @DeleteMapping("/auth")
    public ResponseEntity<String> deleteAllUserFavorites(HttpServletRequest httpServletRequest){
        favoriteService.deleteAllUserFavorites(httpServletRequest);
        return ResponseEntity.ok("All favorites are deleted successfully!");
    }


     */

}
