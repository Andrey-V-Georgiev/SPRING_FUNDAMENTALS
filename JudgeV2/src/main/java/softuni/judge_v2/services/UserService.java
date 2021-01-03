package softuni.judge_v2.services;

import softuni.judge_v2.models.service.UserServiceModel;
import softuni.judge_v2.models.view.UserViewModel;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);

    List<String> findAllUsernames();

    void changeUserRole(String username, String role);

    UserViewModel findSessionUser(HttpSession httpSession);
}
