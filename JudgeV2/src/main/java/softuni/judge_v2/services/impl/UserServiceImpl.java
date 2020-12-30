package softuni.judge_v2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.judge_v2.constants.GlobalConstants;
import softuni.judge_v2.models.binding.UserLoginBindingModel;
import softuni.judge_v2.models.binding.UserRegisterBindingModel;
import softuni.judge_v2.models.entity.Role;
import softuni.judge_v2.models.entity.User;
import softuni.judge_v2.models.service.RoleServiceModel;
import softuni.judge_v2.repositories.UserRepository;
import softuni.judge_v2.services.RoleService;
import softuni.judge_v2.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RoleService roleService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public void register(UserRegisterBindingModel userRegisterBindingModel) {
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
        User user = this.modelMapper.map(userRegisterBindingModel, User.class);
        user.setRole(userRole);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void login(UserLoginBindingModel userLoginBindingModel) {

    }
}
