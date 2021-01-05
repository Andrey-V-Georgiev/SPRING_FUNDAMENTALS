package softuni.car_shop.services;

import softuni.car_shop.models.binding_dtos.UserRegisterBindingModel;
import softuni.car_shop.models.service_dtos.UserServiceModel;

public interface UserService {
    UserServiceModel registerUser(UserRegisterBindingModel userRegisterBindingModel);
}
