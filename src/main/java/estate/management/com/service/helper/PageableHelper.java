package estate.management.com.service.helper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PageableHelper {

    public Pageable getPageableWithProperties(String title, int page, int size, String sort, String type) {
        Sort sortOrder = Sort.by(sort).descending();
        if (Objects.equals(type, "asc")) {
            sortOrder = Sort.by(sort).ascending();
        }
        return PageRequest.of(page, size, sortOrder);
    }


}
