package softuni.car_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.car_shop.models.entities.Brand;


@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {

    Brand findBrandByName(String name);
}
