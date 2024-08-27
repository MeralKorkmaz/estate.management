
package estate.management.com.repository.business;
import estate.management.com.domain.advert.Advert;
import estate.management.com.payload.response.concrete.CityResponse;
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

    @Query("SELECT new estate.management.com.payload.response.concrete.CityResponse(a.location, COUNT(a)) " +
            "FROM Advert a WHERE a.cityId = :cityId GROUP BY a.location")
    List<CityResponse> findCityCountsByCityId(@Param("cityId") int cityId);

    boolean existsByCategoryId(Long id);//I need this code for Category Service class.
}