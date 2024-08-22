package estate.management.com.domain.user;

import lombok.Getter;


@Getter


public enum RoleType {


    ADMIN("Admin"),
    MANAGER("Manager"),
    CUSTOMER("Customer");

    public final String name;
    RoleType(String name) {
        this.name = name;
    }

}

