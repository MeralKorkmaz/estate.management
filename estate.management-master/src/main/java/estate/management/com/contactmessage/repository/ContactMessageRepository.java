
package estate.management.com.contactmessage.repository;

import estate.management.com.contactmessage.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactMessageRepository extends JpaRepository<Contact,Long> {

    @Query("SELECT c FROM Contact c WHERE " +
            "(:q IS NULL OR LOWER(c.message) LIKE LOWER(CONCAT('%', :q, '%'))) AND " +
            "(:status IS NULL OR c.status = :status)")
    Page<Contact> getContactMessagesByCriteria(@Param("q") String q,
                                               @Param("status") Integer status,
                                               Pageable pageable);
}
