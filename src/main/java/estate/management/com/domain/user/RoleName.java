package estate.management.com.domain.user;

import lombok.Getter;


@Getter


public enum RoleName {


    ADMIN("Admin"),
    MANAGER("Manager"),
    CUSTOMER("Customer");

    public final String name;
    RoleName(String name) {
        this.name = name;
    }

}

