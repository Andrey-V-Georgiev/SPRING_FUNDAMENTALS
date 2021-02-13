package softuni.shopping_list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.shopping_list.models.entity.Category;
import softuni.shopping_list.models.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findProductByName(String name);

    List<Product> findProductsByCategory(Category category);

    @Query(value = "SELECT SUM(p.price) FROM Product p")
    BigDecimal findPriceForAllProducts();
}