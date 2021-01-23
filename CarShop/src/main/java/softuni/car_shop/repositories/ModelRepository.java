package softuni.car_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.car_shop.models.entities.Model;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {

    Optional<Model> findModelByName(String name);

    List<Model> findAll();
}
