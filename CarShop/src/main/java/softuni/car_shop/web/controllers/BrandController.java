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
import softuni.car_shop.models.binding_dtos.BrandAddBindingModel;
import softuni.car_shop.models.service_dtos.BrandServiceModel;
import softuni.car_shop.services.AuthService;
import softuni.car_shop.services.BrandService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static softuni.car_shop.constants.GlobalConstants.BINDINGRESULT_PREFIX;

@Controller
@RequestMapping("/brands")
public class BrandController {

    private final ModelMapper modelMapper;
    private final BrandService  brandService;
    private final AuthService authService;

    @Autowired
    public BrandController(ModelMapper modelMapper, BrandService brandService, AuthService authService) {
        this.modelMapper = modelMapper;
        this.brandService = brandService;
        this.authService = authService;
    }

    @GetMapping("/add")
    public String addBrand(Model model, HttpSession httpSession) {
        if(!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        if (!model.containsAttribute("brandAddBindingModel")) {
            model.addAttribute("brandAddBindingModel", new BrandAddBindingModel());
        }
        return "brand-add";
    }

    @PostMapping("/add")
    public String addBrandConfirm(
            @Valid @ModelAttribute("brandAddBindingModel") BrandAddBindingModel brandAddBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            HttpSession httpSession
    ) {
        if(!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        /* If errors in binding result */
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandAddBindingModel", brandAddBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "brandAddBindingModel", bindingResult);
            return "redirect:/brands/add";
        }
        BrandServiceModel savedBrandServiceModel = this.brandService.addBrand(brandAddBindingModel);
        if(savedBrandServiceModel == null) {
            redirectAttributes.addFlashAttribute("brandAddBindingModel", brandAddBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "brandAddBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("existingBrand", true);
            return "redirect:add";
        }
        return "redirect:/home";
    }
}
