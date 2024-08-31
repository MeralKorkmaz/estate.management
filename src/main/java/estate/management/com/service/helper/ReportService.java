package estate.management.com.service.helper;

import estate.management.com.domain.advert.Advert;
import estate.management.com.domain.advert.AdvertType;
import estate.management.com.domain.category.Category;
import estate.management.com.payload.response.ReportResponse;
import estate.management.com.repository.UserRoleRepository;
import estate.management.com.repository.business.AdvertRepository;
import estate.management.com.repository.business.AdvertTypeRepository;
import estate.management.com.repository.business.CategoryRepository;
import estate.management.com.repository.business.TourRequestRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    // Find advert for report by criteria

    //TODO: fix bugs
    public List<Advert> getAdvertsByCriteria(String categoryTitle, String advertTypeTitle, String status, LocalDate startDate, LocalDate endDate) {
        Long categoryId = null;
        Long advertTypeId = null;
         int statusValue = 0;

        if (categoryTitle != null && !categoryTitle.isEmpty()) {
            categoryId = categoryRepository.findByTitle(categoryTitle)
                    .map(Category::getId)
                    .orElse(null);
        }

        if (advertTypeTitle != null && !advertTypeTitle.isEmpty()) {
            advertTypeId = advertTypeRepository.findByTitle(advertTypeTitle)
                    .map(AdvertType::getId)
                    .orElse(null);
        }

        if (status != null && !status.isEmpty()) {
            // Convert status string to corresponding Integer value
            switch (status.toLowerCase()) {
                case "pending":
                    statusValue = 0;
                    break;
                case "activated":
                    statusValue = 1;
                    break;
                case "rejected":
                    statusValue = 2;
                // Add other statuses as needed
                default:
                    throw new IllegalArgumentException("Unknown status value: " + status);
            }
        }

        return advertRepository.findAdvertsByCriteria(
                categoryId,
                advertTypeId,
                statusValue,
                startDate,
                endDate
        );
    }

}
