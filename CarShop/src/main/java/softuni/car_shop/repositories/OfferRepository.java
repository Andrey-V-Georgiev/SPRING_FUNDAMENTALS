package softuni.car_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.car_shop.models.entities.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {
}
