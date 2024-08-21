package estate.management.com.controller.business;

import estate.management.com.payload.response.ReportResponse;
import estate.management.com.service.helper.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/report")
    //@PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ReportResponse getStatistics() {
        return reportService.getStatistics();
    }

}
