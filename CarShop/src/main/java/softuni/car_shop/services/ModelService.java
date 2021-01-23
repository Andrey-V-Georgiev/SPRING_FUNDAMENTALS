package softuni.car_shop.services;

import softuni.car_shop.models.binding_dtos.ModelAddBindingModel;
import softuni.car_shop.models.service_dtos.ModelServiceModel;


public interface ModelService {

    ModelServiceModel addModel(ModelAddBindingModel modelAddBindingModel);

    String[] findAllModelNames();

    ModelServiceModel findModelByName(String modelName);
}
