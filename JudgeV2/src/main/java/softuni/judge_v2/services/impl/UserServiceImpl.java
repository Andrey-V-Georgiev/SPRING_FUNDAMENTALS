package softuni.judge_v2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.judge_v2.constants.GlobalConstants;
import softuni.judge_v2.models.entity.Role;
import softuni.judge_v2.models.entity.User;
import softuni.judge_v2.models.service.HomeworkServiceModel;
import softuni.judge_v2.models.service.RoleServiceModel;
import softuni.judge_v2.models.service.UserServiceModel;
import softuni.judge_v2.models.view.UserViewModel;
import softuni.judge_v2.repositories.UserRepository;
import softuni.judge_v2.services.HomeworkService;
import softuni.judge_v2.services.RoleService;
import softuni.judge_v2.services.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final HomeworkService homeworkService;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RoleService roleService, HomeworkService homeworkService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.homeworkService = homeworkService;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        /* Determine user role */
        Role userRole;
        if (this.userRepository.count() == 0) {
            RoleServiceModel roleServiceModelAdmin = this.roleService.findRoleByName(GlobalConstants.ADMIN_ROLE);
            userRole = this.modelMapper.map(roleServiceModelAdmin, Role.class);
        } else {
            RoleServiceModel roleServiceModelUser = this.roleService.findRoleByName(GlobalConstants.USER_ROLE);
            userRole = this.modelMapper.map(roleServiceModelUser, Role.class);
        }
        /* Save user to DB */
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setRole(userRole);

        User savedUser = this.userRepository.saveAndFlush(user);
        return this.modelMapper.map(savedUser, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {

        return this.userRepository.findUserByUsername(username)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public List<String> findAllUsernames() {
        List<User> allUsers = this.userRepository.findAll();
        return allUsers.stream().map(User::getUsername).collect(Collectors.toList());
    }

    @Override
    public void changeUserRole(String username, String role) {

        UserServiceModel userServiceModel = this.userRepository
                .findUserByUsername(username)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElse(null);

        if (!userServiceModel.getRole().getName().equals(role)) {
            RoleServiceModel roleServiceModel = this.roleService.findRoleByName(role);
            userServiceModel.setRole(roleServiceModel);
            this.userRepository.saveAndFlush(this.modelMapper.map(userServiceModel, User.class));
        }
    }

    @Override
    public UserViewModel findSessionUser(HttpSession httpSession) {
        UserServiceModel userServiceModel = (UserServiceModel) httpSession.getAttribute("userServiceModel");
        UserViewModel userViewModel = this.modelMapper.map(userServiceModel, UserViewModel.class);

        List<HomeworkServiceModel> userHomeworks = this.homeworkService.findHomeworkByAuthorId(userServiceModel.getId());
        String allSendHomeworks = userHomeworks
                .stream()
                .map(h -> h.getExercise().getName())
                .collect(Collectors.joining(",\n"));

        userViewModel.setAllSendHomeworks(allSendHomeworks);
        return userViewModel;
    }
}
