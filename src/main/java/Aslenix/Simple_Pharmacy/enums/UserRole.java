package Aslenix.Simple_Pharmacy.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ADMIN"," bg-danger"),
    CASHIER("CASHIER"," bg-success");

    private final String value;
    private final String cssClass;

    UserRole(String value, String cssClass) {
        this.value = value;
        this.cssClass = cssClass;
    }

}
