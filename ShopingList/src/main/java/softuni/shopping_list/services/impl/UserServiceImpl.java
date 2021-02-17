package softuni.shopping_list.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.shopping_list.models.entity.User;
import softuni.shopping_list.models.service.UserServiceModel;
import softuni.shopping_list.repositories.UserRepository;
import softuni.shopping_list.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    /* ------ Find by username and email ------ */
    @Override
    public UserServiceModel findUserByUsernameAndEmail(String username, String email) {
        UserServiceModel userServiceModel = this.userRepository
                .findByUsernameAndEmail(username, email)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElse(null);
        return userServiceModel;
    }

    /* ------ Find by username and password ------ */
    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {

        UserServiceModel userServiceModel = this.userRepository
                .findByUsernameAndPassword(username, password)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElse(null);

        return userServiceModel;
    }

    /* ------ Register user ------ */
    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User userUnsaved = this.modelMapper.map(userServiceModel, User.class);
        User userSaved = this.userRepository.saveAndFlush(userUnsaved);
        return this.modelMapper.map(userSaved, UserServiceModel.class);
    }
}
