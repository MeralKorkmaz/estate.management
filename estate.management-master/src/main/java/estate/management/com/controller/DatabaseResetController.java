package estate.management.com.controller.business;

import estate.management.com.service.DatabaseResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settings")
public class DatabaseResetController {

    private final DatabaseResetService databaseResetService;

    public DatabaseResetController(DatabaseResetService databaseResetService){
        this.databaseResetService = databaseResetService;
    }

    @PostMapping("db-reset")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<String> resetDatabase(){
        databaseResetService.resetDatabase();
        return ResponseEntity.ok("Database reset successfully");
    }

}
