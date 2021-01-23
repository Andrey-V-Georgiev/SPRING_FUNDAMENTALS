package softuni.car_shop.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.car_shop.enums.CoupeTypeEnum;
import softuni.car_shop.enums.EngineTypeEnum;
import softuni.car_shop.enums.TransmissionTypeEnum;
import softuni.car_shop.models.binding_dtos.OfferAddBindingModel;
import softuni.car_shop.models.service_dtos.OfferServiceModel;
import softuni.car_shop.services.AuthService;
import softuni.car_shop.services.ModelService;
import softuni.car_shop.services.OfferService;
import softuni.car_shop.services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static softuni.car_shop.constants.GlobalConstants.BINDINGRESULT_PREFIX;


@Controller
@RequestMapping(value = "/offers")
public class OfferController {

    private final ModelService modelService;
    private final UserService userService;
    private final OfferService offerService;
    private final AuthService authService;

    @Autowired
    public OfferController(ModelService modelService, UserService userService, OfferService offerService, AuthService authService) {
        this.modelService = modelService;
        this.userService = userService;
        this.offerService = offerService;
        this.authService = authService;
    }

    @GetMapping("/add")
    public String addOffer(Model model, HttpSession httpSession) {
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
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
            RedirectAttributes redirectAttributes,
            HttpSession httpSession
    ) {
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        /* If errors in binding result */
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerAddBindingModel", offerAddBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "offerAddBindingModel", bindingResult);
            return "redirect:/offers/add";
        }
        OfferServiceModel savedOfferServiceModel = this.offerService.addOffer(offerAddBindingModel);
        return "redirect:/home";
    }

    @GetMapping("/all")
    public String allOffers(Model model, HttpSession httpSession) {
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        model.addAttribute("allOffers", this.offerService.findAllOffers());
        return "all";
    }

    @GetMapping("/details/{id}")
    public String offerDetails(
            @PathVariable("id") String id,
            Model model,
            HttpSession httpSession
    ) {
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        model.addAttribute("offer", this.offerService.findOfferById(id));
        return "details";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteOffer(
            @PathVariable("id") String id,
            HttpSession httpSession
    ) {
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        this.offerService.deleteOfferById(id);
        return "redirect:/offers/all";
    }
}
