
package estate.management.com.repository.business;

import estate.management.com.domain.advert.Advert;
import estate.management.com.payload.response.concrete.advert.CategoryResponseForAdvert;
import estate.management.com.payload.response.concrete.advert.CityResponseForAdvert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {

    @Query("SELECT a FROM Advert a WHERE " +
            "(:q IS NULL OR (LOWER(a.title) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER(a.description) LIKE LOWER(CONCAT('%', :q, '%')))) AND " +
            "(:advertTypeId IS NULL OR a.advertTypeId = :advertTypeId) AND " +
            "(:priceStart IS NULL OR a.price >= :priceStart) AND " +
            "(:priceEnd IS NULL OR a.price <= :priceEnd) AND " +
            "(:location IS NULL OR a.location = :location) AND " +
            "(:status IS NULL OR a.status = :status) AND " +
            "a.isActive = true")
    Page<Advert> findAdvertsByCriteria(@Param("q") String q,
                                       @Param("advertTypeId") Integer advertTypeId,
                                       @Param("priceStart") Double priceStart,
                                       @Param("priceEnd") Double priceEnd,
                                       @Param("location") String location,
                                       @Param("status") Integer status,
                                       Pageable pageable);

    @Query("SELECT new estate.management.com.payload.response.concrete.advert.CityResponseForAdvert(c.name,COUNT(a))"
    + "FROM Advert a JOIN a.city c " +
            "GROUP BY c.name")
    List<CityResponseForAdvert> countAdvertsByCity();
    @Query("SELECT new estate.management.com.payload.response.concrete.advert.CategoryResponseForAdvert(c.title, COUNT(a)) " +
            "FROM Advert a JOIN a.category c " +
            "GROUP BY c.title")
    List<CategoryResponseForAdvert> countAdvertsByCategory();


    boolean existsBySlug(String slug);

    Optional<Advert> findBySlug(String slug);
}