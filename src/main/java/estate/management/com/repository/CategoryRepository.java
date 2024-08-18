package estate.management.com.repository;

import estate.management.com.domain.category.Category;
import estate.management.com.payload.response.business.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findByTitleContainingIgnoreCaseAndIsActive(String title, boolean active, Pageable pageable);

    Page<Category> findByIsActive(boolean isActive, Pageable pageable);

    Category getAllCategoriesByTitle(String title);

    List<Category> findByTitleContainingIgnoreCase(String title);
}

