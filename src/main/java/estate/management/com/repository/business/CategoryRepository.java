package estate.management.com.repository.business;

import estate.management.com.domain.category.Category;
import estate.management.com.domain.category.CategoryPropertyKey;
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

    @Query("SELECT c FROM Category c WHERE LOWER(c.title) LIKE LOWER(CONCAT('%', :title, '%')) AND c.isActive = :isActive")
    Page<Category> findByTitleContainingIgnoreCaseAndIsActive(@Param("title") String title, @Param("isActive") boolean isActive, Pageable pageable);
    Page<Category> findByIsActive(boolean isActive, Pageable pageable);
    @Query("SELECT c FROM Category c WHERE c.id = :id")
    Optional<Category> findCategoryById(@Param("id") Long id);
    void deleteById(Long id);
    @Query("SELECT c.categoryPropertyKey FROM Category c WHERE c.id = :id")
    List<CategoryPropertyKey> findPropertyKeysByCategoryId(@Param("id") Long id);
    @Query("SELECT c FROM Category c WHERE LOWER(c.slug) = LOWER(:slug)")
    Optional<Category> findBySlugIgnoreCase(@Param("slug") String slug);
}