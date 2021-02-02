package softuni.andreys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.andreys.enums.CategoryEnums;
import softuni.andreys.models.entity.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findCategoryByName(CategoryEnums name);
}
