package softuni.shopping_list.services;

import softuni.shopping_list.enumerations.CategoryEnum;
import softuni.shopping_list.models.binding.ProductAddBindingModel;
import softuni.shopping_list.models.service.ProductServiceModel;
import softuni.shopping_list.models.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    ProductServiceModel findProductByName(String name);

    List<ProductViewModel> findProductsByCategory(CategoryEnum categoryName);

    BigDecimal findTotalPrice();

    ProductServiceModel addProduct(ProductAddBindingModel productAddBindingModel);

    void deleteItemById(String id);

    void deleteAll();

}
