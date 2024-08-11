package estate.management.com.repository;

import estate.management.com.domain.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Page<Category> findByActive(int active, Pageable pageable);
    Page<Category> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Category> findByTitleContainingIgnoreCaseAndActive(String title, int i, Pageable pageable);
}

