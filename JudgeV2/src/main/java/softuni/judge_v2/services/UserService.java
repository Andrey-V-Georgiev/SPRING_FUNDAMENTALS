package softuni.judge_v2.services;

import softuni.judge_v2.models.binding.UserLoginBindingModel;
import softuni.judge_v2.models.binding.UserRegisterBindingModel;

public interface UserService {

    void register(UserRegisterBindingModel userRegisterBindingModel);

    void login(UserLoginBindingModel userLoginBindingModel);
}
