package estate.management.com.repository;
import estate.management.com.domain.category.CategoryPropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CategoryPropertyValueRepository extends JpaRepository<CategoryPropertyValue, Long> {
}

