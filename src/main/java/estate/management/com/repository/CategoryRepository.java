package estate.management.com.repository;

import estate.management.com.domain.category.Category;
import estate.management.com.payload.response.business.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findByTitleContainingIgnoreCaseAndActive(String title, boolean active, Pageable pageable);

    Page<Category> findByActive(boolean active, Pageable pageable);

    Page<Category> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}

