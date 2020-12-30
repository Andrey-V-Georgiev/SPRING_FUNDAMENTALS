package softuni.judge_v2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.judge_v2.constants.GlobalConstants;
import softuni.judge_v2.models.entity.Role;
import softuni.judge_v2.models.service.RoleServiceModel;
import softuni.judge_v2.repositories.RoleRepository;
import softuni.judge_v2.services.RoleService;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void seedRoles() {
        if(this.roleRepository.count() == 0) {
            Role adminRole = new Role(GlobalConstants.ADMIN_ROLE);
            Role userRole = new Role(GlobalConstants.USER_ROLE);

            this.roleRepository.saveAndFlush(adminRole);
            this.roleRepository.saveAndFlush(userRole);
        }
    }

    @Override
    public RoleServiceModel findRoleByName(String name) {
        RoleServiceModel roleServiceModel = this.roleRepository.findRoleByName(name)
                .map(role -> this.modelMapper.map(role, RoleServiceModel.class))
                .orElse(null);
        return roleServiceModel;
    }
}
