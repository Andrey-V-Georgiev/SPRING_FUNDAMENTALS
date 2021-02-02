package softuni.andreys.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.andreys.models.entity.User;
import softuni.andreys.models.service.UserServiceModel;
import softuni.andreys.repositories.UserRepository;
import softuni.andreys.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserServiceModel findUserByUsernameAndEmail(String username, String email) {
        UserServiceModel userServiceModel = this.userRepository
                .findByUsernameAndEmail(username, email)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElse(null);
        return userServiceModel;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User userUnsaved = this.modelMapper.map(userServiceModel, User.class);
        User userSaved = this.userRepository.saveAndFlush(userUnsaved);
        return this.modelMapper.map(userSaved, UserServiceModel.class);
    }
}
