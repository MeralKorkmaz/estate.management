package estate.management.com.service.business;

import estate.management.com.domain.advert.AdvertType;
import estate.management.com.exception.ResourceNotFoundException;
import estate.management.com.payload.mapper.AdvertTypeMapper;
import estate.management.com.payload.message.ErrorMessages;
import estate.management.com.payload.message.SuccessMessages;
import estate.management.com.payload.request.AdvertTypeRequest;
import estate.management.com.payload.response.AdvertTypeResponse;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.repository.business.AdvertTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertTypeService {

    private final AdvertTypeRepository advertTypeRepository;
    private final AdvertTypeMapper advertTypeMapper;

    // Get all advert types
    public List<AdvertTypeResponse> getAllAdvertTypes() {
        return advertTypeRepository.findAll()
                .stream()
                .map(advertTypeMapper::mapAdvertTypeToAdvertTypeResponse)
                .collect(Collectors.toList());
    }

    // Get advert type by id
    public AdvertTypeResponse getAdvertTypeById(Long id) {
        return advertTypeMapper.mapAdvertTypeToAdvertTypeResponse(isAdvertTypeExist(id));
    }

    // This checks if AdvertType with given id exists.
    private AdvertType isAdvertTypeExist(Long id) {
        return advertTypeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format(ErrorMessages.ADVERT_TYPE_ID_NOT_FOUND_ERROR, id)));
    }


    // Create AdvertType
    public ResponseMessage<AdvertTypeResponse> createAdvertType(
            AdvertTypeRequest advertTypeRequest) {

        AdvertType advertType =
                advertTypeMapper.mapAdvertTypeRequestToAdvertType(advertTypeRequest);

        AdvertType savedAdvertType = advertTypeRepository.save(advertType);

        return ResponseMessage.<AdvertTypeResponse>builder()
                .message(SuccessMessages.ADVERT_TYPE_CREATED_SUCCESS)
                .returnBody(advertTypeMapper.mapAdvertTypeToAdvertTypeResponse(savedAdvertType))
                .httpStatus(HttpStatus.CREATED)
                .build();

    }
}
