package softuni.shopping_list.services;


import softuni.shopping_list.models.service.UserServiceModel;

public interface UserService {

    UserServiceModel findUserByUsernameAndEmail(String username, String email);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    UserServiceModel registerUser(UserServiceModel userServiceModel);
}
