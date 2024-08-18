package estate.management.com.service.business;

import estate.management.com.domain.advert.AdvertType;
import estate.management.com.exception.ResourceNotFoundException;
import estate.management.com.repository.business.AdvertTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertTypeService {

    private final AdvertTypeRepository advertTypeRepository;

    @Autowired
    public AdvertTypeService(AdvertTypeRepository advertTypeRepository) {
        this.advertTypeRepository = advertTypeRepository;
    }

    // Get all advert types
    public List<AdvertType> getAllAdvertTypes() {
        return advertTypeRepository.findAll();
    }

    // Get advert type by id
    /*
    As of spring-boot v 2.7 + both getOne() and getById() are marked as deprecated.
    Therefor I had to use findById with returnType of Optional and add a ResourceNotFoundException.
    */
    public AdvertType getAdvertTypeById(Long id) {
        Optional<AdvertType> optionalAdvertType = advertTypeRepository.findById(id);
        return optionalAdvertType.orElseThrow(() -> new ResourceNotFoundException("Advert type not found"));
    }

    public AdvertType createAdvertType(AdvertType advertType) {
        return advertTypeRepository.save(advertType);
    }
}
