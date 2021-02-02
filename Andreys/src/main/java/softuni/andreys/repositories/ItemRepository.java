package softuni.andreys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.andreys.models.entity.Category;
import softuni.andreys.models.entity.Item;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    Optional<Item> findItemByNameAndCategory(String name, Category category);
}
