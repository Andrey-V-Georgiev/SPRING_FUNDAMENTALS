package softuni.car_shop.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.car_shop.enums.CoupeTypeEnum;
import softuni.car_shop.enums.EngineTypeEnum;
import softuni.car_shop.enums.TransmissionTypeEnum;
import softuni.car_shop.models.binding_dtos.ModelAddBindingModel;
import softuni.car_shop.models.binding_dtos.OfferAddBindingModel;
import softuni.car_shop.models.service_dtos.ModelServiceModel;
import softuni.car_shop.services.ModelService;
import softuni.car_shop.services.UserService;

import javax.validation.Valid;

import static softuni.car_shop.constants.GlobalConstants.BINDINGRESULT_PREFIX;


@Controller
@RequestMapping("/offer")
public class OfferController {

    private final ModelMapper modelMapper;
    private final ModelService modelService;
    private final UserService userService;

    @Autowired
    public OfferController(ModelMapper modelMapper, ModelService modelService, UserService userService) {
        this.modelMapper = modelMapper;
        this.modelService = modelService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        if (!model.containsAttribute("offerAddBindingModel")) {
            model.addAttribute("offerAddBindingModel", new OfferAddBindingModel());
        }
        model.addAttribute("models", this.modelService.findAllModelNames());
        model.addAttribute("engines", EngineTypeEnum.values());
        model.addAttribute("categories", CoupeTypeEnum.values());
        model.addAttribute("transmissions", TransmissionTypeEnum.values());
        model.addAttribute("usernames", this.userService.findAllUsernames());

        return "offer-add";
    }

    @PostMapping("/add")
    public String addOfferConfirm(
            @Valid @ModelAttribute("offerAddBindingModel") OfferAddBindingModel offerAddBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        System.out.println();
        // /* If errors in binding result */
        // if (bindingResult.hasErrors()) {
        //     redirectAttributes.addFlashAttribute("modelAddBindingModel", modelAddBindingModel);
        //     redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "modelAddBindingModel", bindingResult);
        //     return "redirect:/model/add";
        // }
        // ModelServiceModel savedModelServiceModel = this.modelService.addModel(modelAddBindingModel);
        // if(savedModelServiceModel == null) {
        //     redirectAttributes.addFlashAttribute("modelAddBindingModel", modelAddBindingModel);
        //     redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "modelAddBindingModel", bindingResult);
        //     redirectAttributes.addFlashAttribute("existingModel", true);
        //     return "redirect:/model/add";
        // }
        return "redirect:/home";
    }
}
