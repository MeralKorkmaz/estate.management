package estate.management.com.service.business;

import estate.management.com.domain.advert.AdvertType;
import estate.management.com.exception.ResourceNotFoundException;
import estate.management.com.payload.mapper.AdvertTypeMapper;
import estate.management.com.payload.message.ErrorMessages;
import estate.management.com.payload.response.AdvertTypeResponse;
import estate.management.com.repository.business.AdvertTypeRepository;
import lombok.RequiredArgsConstructor;
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

    private AdvertType isAdvertTypeExist(Long id) {
        return advertTypeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format(ErrorMessages.ADVERT_TYPE_ID_NOT_FOUND_ERROR, id)));
    }


    // Create AdvertType
    public AdvertType createAdvertType(AdvertType advertType) {
        return advertTypeRepository.save(advertType);
    }
}
