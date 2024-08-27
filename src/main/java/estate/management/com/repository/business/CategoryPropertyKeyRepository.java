package estate.management.com.repository.business;

import estate.management.com.domain.category.CategoryPropertyKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoryPropertyKeyRepository extends JpaRepository<CategoryPropertyKey, Long> {
    Optional<CategoryPropertyKey> findPropertyKeyById(Long id);
}