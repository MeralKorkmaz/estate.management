package estate.management.com.repository;

import estate.management.com.domain.user.RoleType;
import estate.management.com.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailEquals(String email);

    boolean existsByEmailEquals(String email);

    @Query(value = "SELECT COUNT(u) FROM User u WHERE u.userRole.roleType = ?1")
    long countAdmin(RoleType roleType);

    @Query("SELECT u FROM User u WHERE " +
            "(:q IS NULL OR LOWER(u.firstName) LIKE LOWER(CONCAT('%', :q, '%'))) OR " +
            "(:q IS NULL OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :q, '%'))) OR " +
            "(:q IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :q, '%'))) OR " +
            "(:q IS NULL OR LOWER(u.phone) LIKE LOWER(CONCAT('%', :q, '%')))")
    Page<User> search(@Param("q") String query, Pageable pageable);


}