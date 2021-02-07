package softuni.shopping_list.models.service;

import softuni.shopping_list.enumerations.CategoryEnum;

public class CategoryServiceModel  extends BaseServiceModel {

    private CategoryEnum name;
    private String description;

    public CategoryServiceModel() {
    }

    public CategoryServiceModel(CategoryEnum name) {
        this.name = name;
    }

    public CategoryServiceModel(CategoryEnum name, String description) {
        this.name = name;
        this.description = description;
    }

    public CategoryEnum getName() {
        return name;
    }

    public void setName(CategoryEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
