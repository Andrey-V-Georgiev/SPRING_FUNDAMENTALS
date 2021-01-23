package softuni.car_shop.services;

import softuni.car_shop.models.binding_dtos.OfferAddBindingModel;
import softuni.car_shop.models.service_dtos.OfferServiceModel;

import java.util.List;

public interface OfferService {
    OfferServiceModel  addOffer(OfferAddBindingModel offerAddBindingModel);

    List<OfferServiceModel> findAllOffers();

    OfferServiceModel findOfferById(String id);

    void deleteOfferById(String id);
}
