package softuni.car_shop.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.car_shop.models.binding_dtos.ModelAddBindingModel;
import softuni.car_shop.models.entities.Model;
import softuni.car_shop.models.service_dtos.BrandServiceModel;
import softuni.car_shop.models.service_dtos.ModelServiceModel;
import softuni.car_shop.repositories.ModelRepository;
import softuni.car_shop.services.BrandService;
import softuni.car_shop.services.ModelService;

import java.time.LocalDateTime;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelMapper modelMapper;
    private final ModelRepository modelRepository;
    private final BrandService brandService;

    @Autowired
    public ModelServiceImpl(ModelMapper modelMapper, ModelRepository modelRepository, BrandService brandService) {
        this.modelMapper = modelMapper;
        this.modelRepository = modelRepository;
        this.brandService = brandService;
    }

    @Override
    public ModelServiceModel addModel(ModelAddBindingModel modelAddBindingModel) {
        if(this.modelRepository.findModelByName(modelAddBindingModel.getName()) != null) {
            return null;
        }
        ModelServiceModel modelServiceModel = this.modelMapper.map(modelAddBindingModel, ModelServiceModel.class);
        BrandServiceModel brandByName = this.brandService.findBrandByName(modelAddBindingModel.getBrand());
        modelServiceModel.setBrand(brandByName);
        modelServiceModel.setCreated(LocalDateTime.now());
        modelServiceModel.setModified(LocalDateTime.now());

        Model model = this.modelMapper.map(modelServiceModel, Model.class);
        Model savedModel = this.modelRepository.saveAndFlush(model);
        return this.modelMapper.map(savedModel, ModelServiceModel.class);
    }

    @Override
    public String[] findAllModelNames() {

        String[] modelsNamesAll = this.modelRepository
                .findAll()
                .stream()
                .map(Model::getName)
                .toArray(String[]::new);

        return modelsNamesAll;
    }

}
