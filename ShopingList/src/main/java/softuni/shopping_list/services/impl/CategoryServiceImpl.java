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
        System.out.println("HIT PRE");
        System.out.println(categoryRepository.count());

        if (categoryRepository.count() == 0) {
            /* Create all instances */
            Category shirt = new Category(CategoryEnum.DRINK);
            Category denim = new Category(CategoryEnum.FOOD);
            Category shorts = new Category(CategoryEnum.HOUSEHOLD);
            Category jacket = new Category(CategoryEnum.OTHER);

            /* Save them to DB*/
            this.categoryRepository.saveAndFlush(shirt);
            this.categoryRepository.saveAndFlush(denim);
            this.categoryRepository.saveAndFlush(shorts);
            this.categoryRepository.saveAndFlush(jacket);
        }
    }

    @Override
    public CategoryServiceModel findCategoryByName(CategoryEnum name) {

        CategoryServiceModel categoryServiceModel = this.categoryRepository
                .findCategoryByName(name )
                .map(c -> this.modelMapper.map(c, CategoryServiceModel.class))
                .orElse(null);
        return categoryServiceModel;
    }
}
