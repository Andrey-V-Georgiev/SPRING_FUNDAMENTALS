package softuni.car_shop.models.service_dtos;

import softuni.car_shop.enums.UserRolesEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UserRoleServiceModel extends BaseServiceModel {

    private UserRolesEnum role;

    public UserRoleServiceModel() {
    }

    public UserRoleServiceModel(UserRolesEnum role) {
        this.role = role;
    }

    @Enumerated(EnumType.STRING)
    public UserRolesEnum getRole() {
        return role;
    }

    public void setRole(UserRolesEnum role) {
        this.role = role;
    }
}
