package estate.management.com.service.user;

import estate.management.com.domain.user.RoleType;
import estate.management.com.domain.user.UserRole;
import estate.management.com.exception.ResourceNotFoundException;

import estate.management.com.payload.message.ErrorMessages;
import estate.management.com.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRole getUserRole(RoleType roleType){
        return userRoleRepository.findByEnumRoleEquals(roleType).orElseThrow(()->
                new ResourceNotFoundException(ErrorMessages.ROLE_NOT_FOUND));
    }

    public List<UserRole> getAllUserRole(){
        return userRoleRepository.findAll();
    }
}