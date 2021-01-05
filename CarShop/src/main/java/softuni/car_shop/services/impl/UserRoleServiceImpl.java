package softuni.car_shop.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.car_shop.enums.UserRolesEnum;
import softuni.car_shop.models.entities.UserRole;
import softuni.car_shop.models.service_dtos.UserRoleServiceModel;
import softuni.car_shop.repositories.UserRoleRepository;
import softuni.car_shop.services.UserRoleService;

import javax.annotation.PostConstruct;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(ModelMapper modelMapper, UserRoleRepository userRoleRepository) {
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
    }

    @PostConstruct
    private void seedUserRoles() {
        if(this.userRoleRepository.count() == 0) {
            UserRole adminRole = new UserRole(UserRolesEnum.ADMIN);
            UserRole userRole = new UserRole(UserRolesEnum.USER);

            this.userRoleRepository.saveAndFlush(adminRole);
            this.userRoleRepository.saveAndFlush(userRole);
        }
    }

    @Override
    public UserRoleServiceModel findUserRoleByRole(UserRolesEnum role) {
        UserRoleServiceModel userRoleServiceModel = this.userRoleRepository
                .findUserRoleByRole(role)
                .map(r -> this.modelMapper.map(r, UserRoleServiceModel.class))
                .orElse(null);
        return userRoleServiceModel;
    }
}
