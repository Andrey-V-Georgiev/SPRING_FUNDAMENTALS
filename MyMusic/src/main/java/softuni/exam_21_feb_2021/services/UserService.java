package softuni.exam_21_feb_2021.services;


import softuni.exam_21_feb_2021.models.service.UserServiceModel;

public interface UserService {

    /* 1 */
    UserServiceModel findUserByUsernameAndEmail(String username, String email);

    /* 2 */
    UserServiceModel findByUsernameAndPassword(String username, String password);

    /* 3 */
    UserServiceModel registerUser(UserServiceModel userServiceModel);
}
