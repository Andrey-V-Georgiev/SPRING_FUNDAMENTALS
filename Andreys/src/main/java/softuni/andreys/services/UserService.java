package softuni.andreys.services;

import softuni.andreys.models.service.UserServiceModel;

public interface UserService {
    UserServiceModel findUserByUsernameAndEmail(String username, String email);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    UserServiceModel registerUser(UserServiceModel userServiceModel);
}
