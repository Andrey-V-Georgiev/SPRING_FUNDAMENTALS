package softuni.judge_v2.services;

import softuni.judge_v2.models.service.UserServiceModel;

public interface UserService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);
}
