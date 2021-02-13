package softuni.shopping_list.services;

import softuni.shopping_list.enumerations.CategoryEnum;
import softuni.shopping_list.models.service.CategoryServiceModel;

public interface CategoryService {
    CategoryServiceModel findCategoryByName(CategoryEnum category);
}
