package softuni.car_shop.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.car_shop.models.binding_dtos.BrandAddBindingModel;
import softuni.car_shop.models.entities.Brand;
import softuni.car_shop.models.service_dtos.BrandServiceModel;
import softuni.car_shop.repositories.BrandRepository;
import softuni.car_shop.services.BrandService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BrandServiceImpl implements BrandService {

    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(ModelMapper modelMapper, BrandRepository brandRepository) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
    }

    @Override
    public BrandServiceModel addBrand(BrandAddBindingModel brandAddBindingModel) {
        if (this.brandRepository.findBrandByName(brandAddBindingModel.getName()) != null) {
            return null;
        }
        BrandServiceModel brandServiceModel = this.modelMapper.map(brandAddBindingModel, BrandServiceModel.class);
        brandServiceModel.setCreated(LocalDateTime.now());
        brandServiceModel.setModified(LocalDateTime.now());

        Brand brand = this.modelMapper.map(brandServiceModel, Brand.class);
        Brand savedBrand = this.brandRepository.saveAndFlush(brand);
        return this.modelMapper.map(savedBrand, BrandServiceModel.class);
    }

    @Override
    public List<String> findAllBrandsNames() {
        List<String> brandsNames = this.brandRepository
                .findAll()
                .stream()
                .map(b -> b.getName())
                .collect(Collectors.toList());

        return brandsNames;
    }

    @Override
    public BrandServiceModel findBrandByName(String name) {
        Brand brandByName = this.brandRepository.findBrandByName(name);
        return this.modelMapper.map(brandByName, BrandServiceModel.class);
    }
}
