package softuni.andreys.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.andreys.enums.CategoryEnums;
import softuni.andreys.models.entity.Category;
import softuni.andreys.repositories.CategoryRepository;
import softuni.andreys.services.CategoryService;

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
            Category shirt = new Category(CategoryEnums.SHIRT);
            Category denim = new Category(CategoryEnums.DENIM);
            Category shorts = new Category(CategoryEnums.SHORTS);
            Category jacket = new Category(CategoryEnums.JACKET);

            /* Save them to DB*/
            this.categoryRepository.saveAndFlush(shirt);
            this.categoryRepository.saveAndFlush(denim);
            this.categoryRepository.saveAndFlush(shorts);
            this.categoryRepository.saveAndFlush(jacket);
        }
    }
}
