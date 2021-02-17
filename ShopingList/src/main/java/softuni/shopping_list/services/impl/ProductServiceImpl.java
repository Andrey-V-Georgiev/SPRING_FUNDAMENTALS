package softuni.shopping_list.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.shopping_list.enumerations.CategoryEnum;
import softuni.shopping_list.models.binding.ProductAddBindingModel;
import softuni.shopping_list.models.entity.Category;
import softuni.shopping_list.models.entity.Product;
import softuni.shopping_list.models.service.CategoryServiceModel;
import softuni.shopping_list.models.service.ProductServiceModel;
import softuni.shopping_list.models.view.ProductViewModel;
import softuni.shopping_list.repositories.ProductRepository;
import softuni.shopping_list.services.CategoryService;
import softuni.shopping_list.services.ProductService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    /* ------ Find by name ------ */
    @Override
    public ProductServiceModel findProductByName(String name) {

        ProductServiceModel productServiceModel = this.productRepository
                .findProductByName(name)
                .map(p -> this.modelMapper.map(p, ProductServiceModel.class))
                .orElse(null);

        return productServiceModel;
    }

    /* ------ Find by category ------ */
    @Override
    public List<ProductViewModel> findProductsByCategory(CategoryEnum categoryName) {

        List<ProductViewModel> productViewModels = this.productRepository
                .findProductsByCategory_Name(categoryName)
                .stream()
                .map(p -> this.modelMapper.map(p, ProductViewModel.class))
                .collect(Collectors.toList());

        return productViewModels;
    }

    /* ------ Find total price ------ */
    @Override
    public BigDecimal findTotalPrice() {
        BigDecimal priceForAllProducts = this.productRepository.findPriceForAllProducts();
        return priceForAllProducts;
    }

    /* ------ Add product ------ */
    @Override
    public ProductServiceModel addProduct(ProductAddBindingModel productAddBindingModel) {

        ProductServiceModel productServiceModel = this.modelMapper.map(
                productAddBindingModel,
                ProductServiceModel.class
        );
        /* Set category */
        productServiceModel.setCategory(this.categoryService.findCategoryByName(productAddBindingModel.getCategory()));

        /* Save to DB */
        Product productSaved = this.productRepository.saveAndFlush(
                this.modelMapper.map(productServiceModel, Product.class)
        );
        return this.modelMapper.map(productSaved, ProductServiceModel.class);
    }

    /* ------ Delete ------ */
    @Override
    public void deleteItemById(String id) {
        this.productRepository.deleteById(id);
    }

    /* ------ Delete all ------ */
    @Override
    public void deleteAll() {
        this.productRepository.deleteAll();
    }

}
