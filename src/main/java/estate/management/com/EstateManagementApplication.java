package estate.management.com;

import estate.management.com.domain.user.RoleType;
import estate.management.com.domain.user.UserRole;

import estate.management.com.payload.request.user.concretes.UserRequest;
import estate.management.com.repository.UserRoleRepository;

import estate.management.com.service.user.UserRoleService;
import estate.management.com.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EstateManagementApplication implements CommandLineRunner {
    private final UserRoleService userRoleService;
    private final UserRoleRepository userRoleRepository;
    private final UserService userService;


    public EstateManagementApplication(UserRoleService userRoleService, UserRoleRepository userRoleRepository, UserService userService) {
        this.userRoleService = userRoleService;
        this.userRoleRepository = userRoleRepository;
        this.userService = userService;
    }


    public static void main(String[] args) {
        SpringApplication.run(EstateManagementApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {
        if (userRoleService.getAllUserRole().isEmpty()) {

            UserRole admin = new UserRole();
            admin.setRoleType(RoleType.ADMIN);
            admin.setRoleName("Admin");
            userRoleRepository.save(admin);

            UserRole manager = new UserRole();
            manager.setRoleType(RoleType.MANAGER);
            manager.setRoleName("Mananger");
            userRoleRepository.save(manager);

            UserRole customer = new UserRole();
            customer.setRoleType(RoleType.CUSTOMER);
            customer.setRoleName("Customer");
            userRoleRepository.save(customer);


        }
        if(userService.countAllAdmins()==0){

            UserRequest adminRequest = new UserRequest();

            adminRequest.setEmail("admin_Cin_Ali@admin.com");
            adminRequest.setPasswordHash("Ab123456*");
            adminRequest.setConfirmPassword("Ab123456*");
            adminRequest.setFirstName("Cin");
            adminRequest.setLastName("Ali");
            adminRequest.setPhone("111-111-1111");
            adminRequest.setBuilt_in(Boolean.TRUE);
            userService.saveAdmin(adminRequest);
        }

    }


}


