package softuni.car_shop.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.car_shop.models.binding_dtos.ModelAddBindingModel;
import softuni.car_shop.models.service_dtos.ModelServiceModel;
import softuni.car_shop.repositories.ModelRepository;
import softuni.car_shop.services.ModelService;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelMapper modelMapper;
    private final ModelRepository modelRepository;

    @Autowired
    public ModelServiceImpl(ModelMapper modelMapper, ModelRepository modelRepository) {
        this.modelMapper = modelMapper;
        this.modelRepository = modelRepository;
    }

    @Override
    public ModelServiceModel addModel(ModelAddBindingModel modelAddBindingModel) {
        return null;
    }
}
