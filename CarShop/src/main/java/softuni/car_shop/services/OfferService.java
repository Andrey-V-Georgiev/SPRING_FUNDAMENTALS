package softuni.car_shop.services;

import softuni.car_shop.models.binding_dtos.OfferAddBindingModel;
import softuni.car_shop.models.service_dtos.OfferServiceModel;

public interface OfferService {
    OfferServiceModel  addOffer(OfferAddBindingModel offerAddBindingModel);
}
