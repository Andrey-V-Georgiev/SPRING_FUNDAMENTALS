package softuni.car_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.car_shop.models.entities.Model;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {

    Model findModelByName(String name);

    List<Model> findAll();
}
