package estate.management.com.repository;

import estate.management.com.domain.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    // Method returns number of specific role Types.
    long countByRoleName(String roleName);

}
