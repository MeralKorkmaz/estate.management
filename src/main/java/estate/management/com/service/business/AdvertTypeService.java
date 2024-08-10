package estate.management.com.service.business;

import estate.management.com.domain.advert.AdvertType;
import estate.management.com.repository.business.AdvertTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertTypeService {

    private final AdvertTypeRepository advertTypeRepository;

    @Autowired
    public AdvertTypeService(AdvertTypeRepository advertTypeRepository) {
        this.advertTypeRepository = advertTypeRepository;
    }

    public List<AdvertType> getAllAdvertTypes() {
        return advertTypeRepository.findAll();
    }

}
