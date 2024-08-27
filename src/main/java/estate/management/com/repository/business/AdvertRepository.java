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

    @Repository
    public interface AdvertRepository extends JpaRepository<Advert, Long> {
        @Query("SELECT a FROM Advert a WHERE " +
                "(:q IS NULL OR (LOWER(a.title) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER(a.description) LIKE LOWER(CONCAT('%', :q, '%')))) AND " +
                "(:categoryId IS NULL OR a.categoryId = :categoryId) AND " +
                "(:advertTypeId IS NULL OR a.advertTypeId = :advertTypeId) AND " +
                "(:priceStart IS NULL OR a.price >= :priceStart) AND " +
                "(:priceEnd IS NULL OR a.price <= :priceEnd) AND " +
                "(:location IS NULL OR a.location = :location) AND " +
                "(:status IS NULL OR a.status = :status) AND " +
                "a.isActive = true")
        Page<Advert> findAdvertsByCriteria(@Param("q") String q,
                                           @Param("categoryId") Integer categoryId,
                                           @Param("advertTypeId") Integer advertTypeId,
                                           @Param("priceStart") Double priceStart,
                                           @Param("priceEnd") Double priceEnd,
                                           @Param("location") String location,
                                           @Param("status") Integer status,
                                           Pageable pageable);
        @Query("SELECT new estate.management.com.payload.response.concrete.CityResponse(c.name, COUNT(c)) " +
                "FROM City c WHERE c.id = :cityId GROUP BY c.name")
        List<CityResponse> findCityCountsByCityId(@Param("cityId") Long cityId);

    boolean existsByCategoryId(Long id);//I need this code for category service class
}
