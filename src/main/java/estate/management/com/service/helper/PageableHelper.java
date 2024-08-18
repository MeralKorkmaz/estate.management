package estate.management.com.service.helper;

<<<<<<< HEAD
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
=======
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
>>>>>>> origin/master
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Objects;

<<<<<<< HEAD
@Component
public class PageableHelper {

    public Pageable getPageableWithProperties(String title, int page, int size, String sort, String type) {
        Sort sortOrder = Sort.by(sort).descending();
        if (Objects.equals(type, "asc")) {
            sortOrder = Sort.by(sort).ascending();
        }
        return PageRequest.of(page, size, sortOrder);
    }


=======

@Component
public class PageableHelper {

    public Pageable getPageable (int page, int size, String sort, String type){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        if(Objects.equals(type,"desc")){
            pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        }
        return pageable;
    }
>>>>>>> origin/master
}
