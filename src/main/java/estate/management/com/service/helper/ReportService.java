package estate.management.com.service.helper;

import estate.management.com.payload.response.ReportResponse;
import estate.management.com.repository.UserRoleRepository;
import estate.management.com.repository.business.AdvertRepository;
import estate.management.com.repository.business.AdvertTypeRepository;
import estate.management.com.repository.business.CategoryRepository;
import estate.management.com.repository.business.TourRequestRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Builder
public class ReportService {

    private final CategoryRepository categoryRepository;
    private final AdvertRepository advertRepository;
    private final AdvertTypeRepository advertTypeRepository;
    private final TourRequestRepository tourRequestRepository;
    private final UserRoleRepository userRoleRepository;


    // Methode to get all statistics
    public ReportResponse getStatistics() {
        try {

        long categoryCount = categoryRepository.countByIsActive(true);
        long advertCount = advertRepository.countByIsActive(true);
        long advertTypeCount = advertTypeRepository.count();
        long tourRequestCount = tourRequestRepository.count();
        long customerCount = userRoleRepository.countByRoleName("CUSTOMER");

        return ReportResponse.builder()
                .categories(categoryCount)
                .adverts(advertCount)
                .advertTypes(advertTypeCount)
                .tourRequests(tourRequestCount)
                .customers(customerCount)
                .build();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching statistics", e);
        }
    }

}
