package softuni.car_shop.services;


import softuni.car_shop.enums.UserRolesEnum;
import softuni.car_shop.models.service_dtos.UserRoleServiceModel;

public interface UserRoleService {
    UserRoleServiceModel findUserRoleByRole(UserRolesEnum role);
}
