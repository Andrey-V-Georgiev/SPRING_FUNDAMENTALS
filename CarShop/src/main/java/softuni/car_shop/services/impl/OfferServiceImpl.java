package softuni.car_shop.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.car_shop.models.binding_dtos.OfferAddBindingModel;
import softuni.car_shop.models.entities.Offer;
import softuni.car_shop.models.service_dtos.ModelServiceModel;
import softuni.car_shop.models.service_dtos.OfferServiceModel;
import softuni.car_shop.models.service_dtos.UserServiceModel;
import softuni.car_shop.repositories.OfferRepository;
import softuni.car_shop.services.ModelService;
import softuni.car_shop.services.OfferService;
import softuni.car_shop.services.UserService;

import java.time.LocalDateTime;

@Service
public class OfferServiceImpl implements OfferService {

    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;
    private final ModelService modelService;
    private final UserService userService;

    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, OfferRepository offerRepository, ModelService modelService, UserService userService) {
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
        this.modelService = modelService;
        this.userService = userService;
    }

    @Override
    public OfferServiceModel addOffer(OfferAddBindingModel offerAddBindingModel) {

        OfferServiceModel offerServiceModel = this.modelMapper.map(offerAddBindingModel, OfferServiceModel.class);

        // set  Model model;
        ModelServiceModel modelByName = this.modelService.findModelByName(offerAddBindingModel.getModel());
        offerServiceModel.setModel(modelByName);
        // set User seller;
        UserServiceModel userByUsername = this.userService.findUserByUsername(offerAddBindingModel.getUsername());
        offerServiceModel.setSeller(userByUsername);
        // set LocalDateTime created;
        offerServiceModel.setCreated(LocalDateTime.now());
        // set LocalDateTime modified;
        offerServiceModel.setModified(LocalDateTime.now());


        Offer offer = this.modelMapper.map(offerServiceModel, Offer.class);
        Offer savedOffer = this.offerRepository.saveAndFlush(offer);
        return this.modelMapper.map(savedOffer, OfferServiceModel.class);
    }
}
