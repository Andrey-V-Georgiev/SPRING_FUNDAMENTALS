package softuni.shopping_list.services;


import softuni.shopping_list.models.service.UserServiceModel;

public interface UserService {

    /* 1 */
    UserServiceModel findUserByUsernameAndEmail(String username, String email);

    /* 2 */
    UserServiceModel findByUsernameAndPassword(String username, String password);

    /* 3 */
    UserServiceModel registerUser(UserServiceModel userServiceModel);
}
