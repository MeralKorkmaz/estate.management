package estate.management.com.service.business;

import estate.management.com.domain.advert.AdvertType;
import estate.management.com.exception.ResourceNotFoundException;
import estate.management.com.payload.mapper.AdvertTypeMapper;
import estate.management.com.payload.message.ErrorMessages;
import estate.management.com.payload.message.SuccessMessages;
import estate.management.com.payload.request.AdvertTypeRequest;
import estate.management.com.payload.response.AdvertTypeResponse;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.repository.business.AdvertRepository;
import estate.management.com.repository.business.AdvertTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertTypeService {

    private final AdvertTypeRepository advertTypeRepository;
    private final AdvertTypeMapper advertTypeMapper;
    private final AdvertRepository advertRepository;

    // Get all advert types
    public List<AdvertTypeResponse> getAllAdvertTypes() {
        List<AdvertType> advertTypes = advertTypeRepository.findAll();
        return mapToAdvertTypeResponseList(advertTypes);
    }

    // Get advert type by id
    public AdvertTypeResponse getAdvertTypeById(Long id) {
        return advertTypeMapper.mapAdvertTypeToAdvertTypeResponse(findAdvertTypeById(id));
    }


    // Create AdvertType
    public ResponseMessage<AdvertTypeResponse> createAdvertType(
            AdvertTypeRequest advertTypeRequest) {
        // check if title is null
        if (advertTypeRequest.getTitle() == null) {
            throw new IllegalArgumentException("AdvertTypeRequest cannot be null");
        }

        AdvertType advertType =
                advertTypeMapper.mapAdvertTypeRequestToAdvertType(advertTypeRequest);

        AdvertType savedAdvertType = advertTypeRepository.save(advertType);

        return ResponseMessage.<AdvertTypeResponse>builder()
                .message(SuccessMessages.ADVERT_TYPE_CREATED_SUCCESS)
                .object(advertTypeMapper.mapAdvertTypeToAdvertTypeResponse(savedAdvertType))
                .status(HttpStatus.CREATED)
                .build();

    }

    // Update AdvertType By Id
    public ResponseMessage<AdvertTypeResponse> updateAdvertTypeById(
            Long id, AdvertTypeRequest advertTypeRequest) {

        //Finding existing advertType with id
        AdvertType existingAdvertType = findAdvertTypeById(id);

        //Updating existing AdvertType with new values from AdvertTypeRequest
        existingAdvertType.setTitle(advertTypeRequest.getTitle());

        //Saving the updated AdvertType
        AdvertType updatedAdvertType = advertTypeRepository.save(existingAdvertType);

        //Returning response message
        return ResponseMessage.<AdvertTypeResponse>builder()
                .message(SuccessMessages.ADVERT_TYPE_UPDATED_SUCCESS)
                .object(advertTypeMapper.mapAdvertTypeToAdvertTypeResponse(updatedAdvertType))
                .status(HttpStatus.OK)
                .build();
    }

    // Delete AdvertType (only if no advert is related to the advertType)
    @Transactional(
            timeout = 30, //30 seconds timeout
            rollbackFor = {Exception.class} // Rollback for any exeption occuring
    )
    public ResponseMessage<AdvertTypeResponse> deleteAdvertTypeById(Long id) {

            AdvertType advertType = findAdvertTypeById(id);

            //check if related to an advert record
            boolean isAdvertTypeRelatedToAnAdvert = advertRepository
                    .existsByAdvertTypeId(advertType.getId());
            if (isAdvertTypeRelatedToAnAdvert) {
                return ResponseMessage.<AdvertTypeResponse>builder()
                        .message(ErrorMessages.ADVERT_TYPE_HAS_ASSOCIATED_ADVERTS_ERROR)
                        .status(HttpStatus.BAD_REQUEST)
                        .build();
            }

            //before deleting map advertType to advertTypeResponse
            AdvertTypeResponse advertTypeResponse = advertTypeMapper
                    .mapAdvertTypeToAdvertTypeResponse(advertType);

            //delete
            advertTypeRepository.delete(advertType);

            //return
            return ResponseMessage.<AdvertTypeResponse>builder()
                    .message(SuccessMessages.ADVERT_TYPE_DELETED_SUCCESS)
                    .object(advertTypeResponse)
                    .status(HttpStatus.OK)
                    .build();

    }



    // HELPER METHODS

    // This checks if AdvertType with given id exists and returns them.
    private AdvertType findAdvertTypeById(Long id) {
        return advertTypeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String
                                .format(ErrorMessages.ADVERT_TYPE_ID_NOT_FOUND_ERROR, id)));
    }

    // Maps a AdvertTypeList to a AdvertTypeResponseList.
    private List<AdvertTypeResponse> mapToAdvertTypeResponseList(
            List<AdvertType> advertTypes) {
        return advertTypes.stream()
                .map(advertTypeMapper::mapAdvertTypeToAdvertTypeResponse)
                .collect(Collectors.toList());
    }
}
