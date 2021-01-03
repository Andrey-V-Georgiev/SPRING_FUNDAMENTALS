package softuni.judge_v2.models.view;

import softuni.judge_v2.models.service.BaseServiceModel;

public class RoleViewModel extends BaseServiceModel {

    private String name;

    public RoleViewModel() {
    }

    public RoleViewModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
