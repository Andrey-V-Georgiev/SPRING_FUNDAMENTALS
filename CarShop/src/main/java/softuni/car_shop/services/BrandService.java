package softuni.car_shop.services;

import softuni.car_shop.models.binding_dtos.BrandAddBindingModel;
import softuni.car_shop.models.service_dtos.BrandServiceModel;

import java.util.List;

public interface BrandService {

    BrandServiceModel addBrand(BrandAddBindingModel brandAddBindingModel);

    List<String> findAllBrandsNames();

    BrandServiceModel findBrandByName(String name);
}
