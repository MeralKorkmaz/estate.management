package estate.management.com.repository;

import estate.management.com.domain.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findByTitleContainingIgnoreCaseAndIsActive(String title, boolean active, Pageable pageable);

    Page<Category> findByIsActive(boolean isActive, Pageable pageable);

    Category getAllCategoriesByTitle(String title);

    List<Category> findByTitleContainingIgnoreCase(String title);
    @Query("SELECT c FROM Category c WHERE c.id = :id")
    Optional<Category> findCategoryById(@Param("id") Long id);

    void deleteById(Long id);


}

