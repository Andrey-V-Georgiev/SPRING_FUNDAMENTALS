package softuni.shopping_list.services;

import softuni.shopping_list.enumerations.CategoryEnum;
import softuni.shopping_list.models.binding.ProductAddBindingModel;
import softuni.shopping_list.models.service.ProductServiceModel;
import softuni.shopping_list.models.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    ProductServiceModel findProductByName(String name);

    ProductServiceModel addProduct(ProductAddBindingModel productAddBindingModel);

    List<ProductViewModel> findProductsByCategory(CategoryEnum categoryName);

    void deleteItemById(String id);

    void deleteAll();

    BigDecimal findPriceForAllProducts();

}
