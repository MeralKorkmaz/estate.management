package estate.management.com.controller.business;

import estate.management.com.domain.advert.Advert;
import estate.management.com.domain.advert.AdvertType;
import estate.management.com.domain.category.Category;
import estate.management.com.payload.response.ReportResponse;
import estate.management.com.service.helper.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/report")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ReportResponse getStatistics() {
        return reportService.getStatistics();
    }


    // Get adverts by criteria for report
    @GetMapping("/report/adverts")
    //@PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public List<Advert> getAdverts(@RequestParam(required = false) String category,
                                   @RequestParam(required = false) String advertType,
                                   @RequestParam(required = false) String status,
                                   @RequestParam(required = false) String startDate,
                                   @RequestParam(required = false) String endDate) {
        LocalDate start = startDate != null ? LocalDate.parse(startDate) : null;
        LocalDate end = endDate != null ? LocalDate.parse(endDate) : null;
        return reportService.getAdvertsByCriteria(category, advertType, status, start, end);
    }
}
