package softuni.car_shop.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.car_shop.enums.UserRolesEnum;
import softuni.car_shop.models.binding_dtos.UserRegisterBindingModel;
import softuni.car_shop.models.entities.User;
import softuni.car_shop.models.service_dtos.UserRoleServiceModel;
import softuni.car_shop.models.service_dtos.UserServiceModel;
import softuni.car_shop.repositories.UserRepository;
import softuni.car_shop.services.UserRoleService;
import softuni.car_shop.services.UserService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, UserRoleService userRoleService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
    }

    @Override
    public UserServiceModel registerUser(UserRegisterBindingModel userRegisterBindingModel) {
        /* Determine user role */
        UserRoleServiceModel userRoleServiceModel = null;
        if (userRepository.count() == 0) {
            userRoleServiceModel = this.userRoleService.findUserRoleByRole(UserRolesEnum.ADMIN);
        } else {
            userRoleServiceModel = this.userRoleService.findUserRoleByRole(UserRolesEnum.USER);
        }
        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        userServiceModel.setRole(userRoleServiceModel);
        userServiceModel.setCreated(LocalDateTime.now());
        userServiceModel.setModified(LocalDateTime.now());
        userServiceModel.setActive(true);

        User user = this.modelMapper.map(userServiceModel, User.class);
        User savedUser = this.userRepository.saveAndFlush(user);
        return this.modelMapper.map(savedUser, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        User user = this.userRepository.findUserByUsernameAndPassword(username, password).orElse(null);
        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
        return userServiceModel;
    }
}
