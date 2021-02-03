package softuni.andreys.models.service;

import softuni.andreys.enums.CategoryEnums;

public class CategoryServiceModel extends BaseServiceModel {

    private CategoryEnums name;
    private String description;

    public CategoryServiceModel() {
    }

    public CategoryServiceModel(CategoryEnums name, String description) {
        this.name = name;
        this.description = description;
    }

    public CategoryEnums getName() {
        return name;
    }

    public void setName(CategoryEnums name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
