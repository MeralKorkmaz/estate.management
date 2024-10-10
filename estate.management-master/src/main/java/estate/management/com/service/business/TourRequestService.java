package estate.management.com.service.business;

import estate.management.com.domain.TourRequest;
import estate.management.com.domain.advert.Advert;
import estate.management.com.domain.user.RoleType;
import estate.management.com.domain.user.User;
import estate.management.com.payload.mapper.TourRequestMapper;
import estate.management.com.payload.message.ErrorMessages;
import estate.management.com.payload.request.TourRequestRequestDto;
import estate.management.com.payload.response.TourRequestResponse;
import estate.management.com.payload.response.user.concretes.UserResponse;
import estate.management.com.repository.business.TourRequestRepository;
import estate.management.com.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourRequestService {
    private final TourRequestRepository tourRequestRepository;
    private final TourRequestMapper tourRequestMapper;
    private final AdvertService advertService;
    private  UserService userService;

    @Autowired
    public TourRequestService(@Lazy TourRequestRepository tourRequestRepository, TourRequestMapper tourRequestMapper, AdvertService advertService, UserService userService){
        this.tourRequestRepository = tourRequestRepository;
        this.tourRequestMapper = tourRequestMapper;
        this.advertService = advertService;
        this.userService = userService;
    }

    public Page<TourRequest> searchUserTourRequests(String query, int page, int size, String sort, String type, HttpServletRequest request) {
        String mail = (String) request.getAttribute("email");
        UserResponse user = userService.findByEmail(mail);
        Long userId = user.getId();

        Sort sorted = Sort.by(Sort.Direction.fromString(type), sort);
        Pageable pageable = PageRequest.of(page, size, sorted);
        return tourRequestRepository.searchUserTourRequests(query, userId, pageable);

    }

    public Page<TourRequest> getTourRequestsByAdmin(String q, int page, int size, String sort, String type, HttpServletRequest request) {
        String mail = (String) request.getAttribute("email");
        UserResponse user = userService.findByEmail(mail);
        String userRole = user.getRole();
        if ((!userRole.equals(RoleType.ADMIN) || userRole.equals(RoleType.MANAGER))) {
            throw new RuntimeException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
        }

        Sort sorted = Sort.by(Sort.Direction.fromString(type), sort);
        Pageable pageable = PageRequest.of(page, size, sorted);
        return tourRequestRepository.getAllTourRequestsByAdmin(q, pageable);
    }


    public ResponseEntity<TourRequestResponse> getAuthenticatedUserTourRequest(Long tourRequestId, HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        UserResponse user = userService.findByEmail(email);
        Long userId = user.getId();

        Optional<TourRequest> opTourRequest = tourRequestRepository.findById(tourRequestId);
        if (opTourRequest.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            TourRequest tourRequest = opTourRequest.get();


            return new ResponseEntity<>(tourRequestMapper.mapTourRequestToTourRequestResponse(tourRequest), HttpStatus.OK);
        }
    }

    public ResponseEntity<TourRequestResponse> getAuthenticatedUserTourRequestByAdmin(Long tourRequestId, HttpServletRequest request) {

        String email = (String) request.getAttribute("email");
        UserResponse userResponse = userService.findByEmail(email);
        if (userResponse.getRole().equals(RoleType.ADMIN) || userResponse.getRole().equals(RoleType.MANAGER)) {
            Optional<TourRequest> opTourRequest = tourRequestRepository.findById(tourRequestId);
            if (opTourRequest.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                TourRequest tourRequest = opTourRequest.get();
                return new ResponseEntity<>(tourRequestMapper.mapTourRequestToTourRequestResponse(tourRequest), HttpStatus.OK);
            }
        } else {
            throw new RuntimeException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
        }

    }

    public ResponseEntity<TourRequestResponse> createTourRequest(TourRequestRequestDto tourRequestRequestDto, HttpServletRequest httpServletRequest) {
        String email = (String) httpServletRequest.getAttribute("email");
        UserResponse userResponse = userService.findByEmail(email);
        int advertId = tourRequestRequestDto.getAdvert_id();
        Advert advert = advertService.findById(advertId);

        TourRequest tourRequest = new TourRequest();
        tourRequest.setTourDate(tourRequestRequestDto.getTourDate());
        tourRequest.setTourTime(tourRequestRequestDto.getTourTime());
        tourRequest.setAdvert(advert);
        tourRequest.setGuestUserId(Math.toIntExact(userResponse.getId()));
        tourRequest.setOwnerUserId(advert.getUserId());
        tourRequest.setStatus(0);
        tourRequestRepository.save(tourRequest);
        return new ResponseEntity<>(tourRequestMapper.mapTourRequestToTourRequestResponse(tourRequest), HttpStatus.CREATED);

    }

    public ResponseEntity<TourRequestResponse> updateTourRequest(Long tourRequestId, TourRequestRequestDto tourRequestRequestDto, HttpServletRequest httpServletRequest) {
        TourRequest tourRequest = this.findTourRequest(tourRequestId);
        int advertID = tourRequestRequestDto.getAdvert_id();
        Advert advert = advertService.findById(advertID);
        int advertStatus = advert.getStatus();

        if (advertStatus != 1) {
            throw new RuntimeException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
        }

        int tourRequestStatus = tourRequest.getStatus();

        if (tourRequestStatus != 0 || tourRequestStatus != 2) {
            throw new RuntimeException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
        }
        String email = (String) httpServletRequest.getAttribute("email");
        UserResponse userResponse = userService.findByEmail(email);
        if (userResponse.getId() != tourRequestId) {
            throw new RuntimeException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);

        }
        tourRequest.setTourDate(tourRequestRequestDto.getTourDate());
        tourRequest.setTourTime(tourRequestRequestDto.getTourTime());
        tourRequest.setAdvert(advert);
        tourRequest.setStatus(0);
        tourRequestRepository.save(tourRequest);
        return new ResponseEntity<>(tourRequestMapper.mapTourRequestToTourRequestResponse(tourRequest), HttpStatus.OK);
    }

    public TourRequest findTourRequest(Long tourRequestId) {
        Optional<TourRequest> opTourRequest = tourRequestRepository.findById(tourRequestId);
        if (opTourRequest.isEmpty()) {
            throw new RuntimeException(ErrorMessages.TOUR_REQUEST_NOT_FOUND_MESSAGE);
        } else {
            return opTourRequest.get();
        }
    }

    public ResponseEntity<TourRequestResponse> cancelTourRequest(Long tourRequestId, HttpServletRequest httpServletRequest) {
        TourRequest tourRequest = this.findTourRequest(tourRequestId);

        String email = (String) httpServletRequest.getAttribute("email");
        UserResponse userResponse = userService.findByEmail(email);
        if (userResponse.getId()!= tourRequest.getOwnerUserId()) {
            throw new RuntimeException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);

        }

        tourRequest.setStatus(3);
        tourRequestRepository.save(tourRequest);
        return new ResponseEntity<>(tourRequestMapper.mapTourRequestToTourRequestResponse(tourRequest), HttpStatus.OK);
    }

    public ResponseEntity<TourRequestResponse> approveTourRequest(Long tourRequestId, HttpServletRequest httpServletRequest) {
        TourRequest tourRequest = this.findTourRequest(tourRequestId);

        String email = (String) httpServletRequest.getAttribute("email");
        UserResponse userResponse = userService.findByEmail(email);
        if (userResponse.getId()!= tourRequest.getOwnerUserId()) {
            throw new RuntimeException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);

        }

        tourRequest.setStatus(1);
        tourRequestRepository.save(tourRequest);
        return new ResponseEntity<>(tourRequestMapper.mapTourRequestToTourRequestResponse(tourRequest), HttpStatus.OK);
    }

    public ResponseEntity<TourRequestResponse> declineTourRequest(Long tourRequestId, HttpServletRequest httpServletRequest) {
        TourRequest tourRequest = this.findTourRequest(tourRequestId);

        String email = (String) httpServletRequest.getAttribute("email");
        UserResponse userResponse = userService.findByEmail(email);
        if (userResponse.getId()!= tourRequest.getOwnerUserId()) {
            throw new RuntimeException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);

        }

        tourRequest.setStatus(2);
        tourRequestRepository.save(tourRequest);
        return new ResponseEntity<>(tourRequestMapper.mapTourRequestToTourRequestResponse(tourRequest), HttpStatus.OK);
    }

    public ResponseEntity<TourRequestResponse> deleteTourRequest(Long tourRequestId) {
        TourRequest tourRequest = this.findTourRequest(tourRequestId);

        tourRequestRepository.deleteById(tourRequestId);
        return new ResponseEntity<>(tourRequestMapper.mapTourRequestToTourRequestResponse(tourRequest),HttpStatus.NO_CONTENT);
    }
}
