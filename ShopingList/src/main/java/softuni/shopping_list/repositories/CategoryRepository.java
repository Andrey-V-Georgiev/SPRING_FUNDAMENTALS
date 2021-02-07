package softuni.shopping_list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.shopping_list.enumerations.CategoryEnum;
import softuni.shopping_list.models.entity.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>  {

    Optional<Category> findCategoryByName(CategoryEnum name);
}
