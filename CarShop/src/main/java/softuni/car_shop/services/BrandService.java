package softuni.car_shop.services;

import softuni.car_shop.models.binding_dtos.BrandAddBindingModel;
import softuni.car_shop.models.service_dtos.BrandServiceModel;

public interface BrandService {

    BrandServiceModel addBrand(BrandAddBindingModel brandAddBindingModel);
}
