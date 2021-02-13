package softuni.shopping_list.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.shopping_list.enumerations.CategoryEnum;
import softuni.shopping_list.models.entity.Category;
import softuni.shopping_list.models.service.CategoryServiceModel;
import softuni.shopping_list.repositories.CategoryRepository;
import softuni.shopping_list.services.CategoryService;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void seedCategories() {
        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryEnum.values()).forEach(categoryName -> {
                Category category = new Category(categoryName, String.format("Description for %s", categoryName));
                this.categoryRepository.saveAndFlush(category);
            });
        }
    }

    @Override
    public CategoryServiceModel findCategoryByName(CategoryEnum name) {

        CategoryServiceModel categoryServiceModel = this.categoryRepository
                .findCategoryByName(name)
                .map(c -> this.modelMapper.map(c, CategoryServiceModel.class))
                .orElse(null);
        return categoryServiceModel;
    }
}
