package softuni.judge_v2.services;

import softuni.judge_v2.models.service.RoleServiceModel;

public interface RoleService {

    RoleServiceModel findRoleByName(String name);
}
