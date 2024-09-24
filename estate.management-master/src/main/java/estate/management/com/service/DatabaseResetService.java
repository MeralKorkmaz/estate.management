package estate.management.com.service;

import estate.management.com.contactmessage.entity.Contact;
import estate.management.com.domain.Favorite;
import estate.management.com.domain.Image;
import estate.management.com.domain.TourRequest;
import estate.management.com.domain.administrative.City;
import estate.management.com.domain.administrative.Country;
import estate.management.com.domain.administrative.District;
import estate.management.com.domain.advert.Advert;
import estate.management.com.domain.advert.AdvertType;
import estate.management.com.domain.category.Category;
import estate.management.com.domain.category.CategoryPropertyKey;
import estate.management.com.domain.category.CategoryPropertyValue;
import estate.management.com.domain.user.Log;
import estate.management.com.domain.user.Role;
import estate.management.com.domain.user.User;
import estate.management.com.domain.user.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class DatabaseResetService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void resetDatabase(){
        List<Class<?>> entities = List.of(Contact.class, City.class, Country.class, District.class, Advert.class,
                AdvertType.class, Category.class, CategoryPropertyKey.class, CategoryPropertyValue.class, Log.class,
                Role.class, User.class, Favorite.class, Image.class, TourRequest.class, UserRole.class);

        for (Class<?> entityClass : entities){
            String entityName = entityClass.getSimpleName();
            String jpql = "DELETE FROM " + entityName + " e WHERE e.builtIn = false";
            entityManager.createQuery(jpql).executeUpdate();
        }
    }

}
