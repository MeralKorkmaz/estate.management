package estate.management.com.controller.business;

import estate.management.com.domain.TourRequest;
import estate.management.com.payload.request.TourRequestRequestDto;
import estate.management.com.payload.response.TourRequestResponse;
import estate.management.com.service.business.TourRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tourRequest")
@RequiredArgsConstructor
public class TourRequestController {

    private final TourRequestService tourRequestService;

    @GetMapping("/tour-requests/auth")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Page<TourRequest> getTourRequests(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "category_id") String sort,
            @RequestParam(defaultValue = "desc") String type,
            HttpServletRequest request) {

        return tourRequestService.searchUserTourRequests(q, page, size, sort, type, request);
    }


    @GetMapping("/tour-requests/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public Page<TourRequest> getTourRequestsByAdmin(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "category_id") String sort,
            @RequestParam(defaultValue = "desc") String type,
            HttpServletRequest request) {

        return tourRequestService.getTourRequestsByAdmin(q, page, size, sort, type, request);
    }

    @GetMapping("/tour-requests/{tourRequestId}/auth")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<TourRequestResponse> getAuthenticatedUserTourRequest(@PathVariable Long tourRequestId, HttpServletRequest request) {
        return tourRequestService.getAuthenticatedUserTourRequest(tourRequestId, request);

    }

    @GetMapping("/tour-requests/{tourRequestId}/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<TourRequestResponse> getAuthenticatedUserTourRequestByAdmin(@PathVariable Long tourRequestId, HttpServletRequest request) {
        return tourRequestService.getAuthenticatedUserTourRequestByAdmin(tourRequestId, request);

    }

    @PostMapping("/tour-requests")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<TourRequestResponse> createTourRequest(@Valid @RequestBody TourRequestRequestDto tourRequestRequestDto, HttpServletRequest httpServletRequest) {
        return tourRequestService.createTourRequest(tourRequestRequestDto, httpServletRequest);
    }

    @PutMapping("/tour-requests/{tourRequestId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<TourRequestResponse> updateTourRequest(@PathVariable Long tourRequestId, @Valid @RequestBody TourRequestRequestDto tourRequestRequestDto, HttpServletRequest httpServletRequest) {
        return tourRequestService.updateTourRequest(tourRequestId, tourRequestRequestDto, httpServletRequest);
    }

    @PatchMapping("/tour-requests/{tourRequestId}/cancel")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<TourRequestResponse> cancelTourRequest(@PathVariable Long tourRequestId, HttpServletRequest httpServletRequest) {
        return tourRequestService.cancelTourRequest(tourRequestId, httpServletRequest);
    }

    @PatchMapping("/tour-requests/{tourRequestId}/approve")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<TourRequestResponse> approveTourRequest(@PathVariable Long tourRequestId, HttpServletRequest httpServletRequest) {
        return tourRequestService.approveTourRequest(tourRequestId, httpServletRequest);
    }

    @PatchMapping("/tour-requests/{tourRequestId}/decline")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<TourRequestResponse> declineTourRequest(@PathVariable Long tourRequestId, HttpServletRequest httpServletRequest) {
        return tourRequestService.declineTourRequest(tourRequestId, httpServletRequest);
    }

    @DeleteMapping("/tour-request/{tourRequestId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<TourRequestResponse> deleteTourRequest(@PathVariable Long tourRequestId) {
        return tourRequestService.deleteTourRequest(tourRequestId);
    }

}
