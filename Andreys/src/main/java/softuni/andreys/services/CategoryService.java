package softuni.andreys.services;

import softuni.andreys.enums.CategoryEnums;
import softuni.andreys.models.service.CategoryServiceModel;

import java.util.List;

public interface CategoryService {

    CategoryServiceModel findCategoryByName(CategoryEnums name);
}
