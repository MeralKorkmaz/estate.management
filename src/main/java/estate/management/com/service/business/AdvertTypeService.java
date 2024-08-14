package estate.management.com.service.business;

import estate.management.com.domain.advert.AdvertType;
import estate.management.com.exception.ResourceNotFoundException;
import estate.management.com.repository.business.AdvertTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertTypeService {

    private final AdvertTypeRepository advertTypeRepository;

    /*Why is there no @Autowired Constructor? -> Since @RequiredArgsConstructor generates constructor automatically,
    Spring will use it for dependency injection. Therefor no need for a constructor to write.
     */

    // Get all advert types
    public List<AdvertType> getAllAdvertTypes() {
        return advertTypeRepository.findAll();
    }

    // Get advert type by id
    public AdvertType getAdvertTypeById(Long id) {
        Optional<AdvertType> optionalAdvertType = advertTypeRepository.findById(id);
        return optionalAdvertType.orElseThrow(() -> new ResourceNotFoundException("Advert type not found"));
    }

    public AdvertType createAdvertType(AdvertType advertType) {
        return advertTypeRepository.save(advertType);
    }
}
