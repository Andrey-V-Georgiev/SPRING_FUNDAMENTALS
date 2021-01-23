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
import softuni.car_shop.models.binding_dtos.ModelAddBindingModel;
import softuni.car_shop.models.service_dtos.ModelServiceModel;
import softuni.car_shop.services.BrandService;
import softuni.car_shop.services.ModelService;

import javax.validation.Valid;

import java.util.List;

import static softuni.car_shop.constants.GlobalConstants.BINDINGRESULT_PREFIX;

@Controller
@RequestMapping("/models")
public class ModelController {

    private final ModelMapper modelMapper;
    private final ModelService modelService;
    private final BrandService brandService;

    @Autowired
    public ModelController(ModelMapper modelMapper, ModelService modelService, BrandService brandService) {
        this.modelMapper = modelMapper;
        this.modelService = modelService;
        this.brandService = brandService;
    }

    @GetMapping("/add")
    public String addModel(Model model) {
        if (!model.containsAttribute("modelAddBindingModel")) {
            model.addAttribute("modelAddBindingModel", new ModelAddBindingModel());
        }
        List<String> allBrandsNames = brandService.findAllBrandsNames();
        model.addAttribute("allBrandsNames", allBrandsNames);
        return "model-add";
    }

    @PostMapping("/add")
    public String addModelConfirm(
            @Valid @ModelAttribute("modelAddBindingModel") ModelAddBindingModel modelAddBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        System.out.println();
        /* If errors in binding result */
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("modelAddBindingModel", modelAddBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "modelAddBindingModel", bindingResult);
            return "redirect:/models/add";
        }
        ModelServiceModel modelByName = this.modelService.findModelByName(modelAddBindingModel.getName());
        if(modelByName != null) {
            redirectAttributes.addFlashAttribute("modelAddBindingModel", modelAddBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "modelAddBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("existingModel", true);
            return "redirect:/models/add";
        }
        ModelServiceModel savedModelServiceModel = this.modelService.addModel(modelAddBindingModel);

        return "redirect:/home";
    }
}
