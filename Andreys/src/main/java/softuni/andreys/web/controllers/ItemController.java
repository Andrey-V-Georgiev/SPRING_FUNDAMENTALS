package softuni.andreys.web.controllers;

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
import softuni.andreys.enums.CategoryEnums;
import softuni.andreys.enums.GenderEnums;
import softuni.andreys.models.binding.ItemAddBindingModel;
import softuni.andreys.models.service.CategoryServiceModel;
import softuni.andreys.services.CategoryService;
import softuni.andreys.services.ItemService;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import static softuni.andreys.constants.GlobalConstants.BINDINGRESULT_PREFIX;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ModelMapper modelMapper;
    private final ItemService itemService;
    private final CategoryService categoryService;

    @Autowired
    public ItemController(ModelMapper modelMapper, ItemService itemService, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    /* Add */
    @GetMapping("/add")
    public String addItem(Model model) {
        if (!model.containsAttribute("itemAddBindingModel")) {
            model.addAttribute("itemAddBindingModel", new ItemAddBindingModel());
        }
        model.addAttribute("allCategories", CategoryEnums.values());
        model.addAttribute("allGenders", GenderEnums.values());
        return "add-item";
    }

    @PostMapping("/add")
    public String addItemConfirm(
            @Valid @ModelAttribute("itemAddBindingModel") ItemAddBindingModel itemAddBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        System.out.println();
        /* IF ERRORS */
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "itemAddBindingModel", bindingResult);
            return "redirect:/items/add";
        }
        /* IF ITEM ALREADY EXISTS */
        if (this.itemService.findItemByName(itemAddBindingModel.getName()) != null) {
            redirectAttributes.addFlashAttribute("itemAlreadyExists", true);
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "itemAddBindingModel", bindingResult);
            return "redirect:/items/add";
        }
        /* SAVE TO DB */
        this.itemService.addItem(itemAddBindingModel);
        return "redirect:/home";
    }

    /* Details */


}
