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
import softuni.car_shop.models.binding_dtos.UserLoginBindingModel;
import softuni.car_shop.models.binding_dtos.UserRegisterBindingModel;
import softuni.car_shop.models.service_dtos.UserServiceModel;
import softuni.car_shop.services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static softuni.car_shop.constants.GlobalConstants.BINDINGRESULT_PREFIX;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(
            @Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        /* If errors in binding result */
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "userRegisterBindingModel", bindingResult);
            return "redirect:/users/register";
        }
        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        this.userService.registerUser(userRegisterBindingModel);
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(
            @Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            HttpSession httpSession
    ) {
        /* If errors in binding result */
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "userLoginBindingModel", bindingResult);
            return "redirect:/users/login";
        }
        UserServiceModel userServiceModel = this.userService.findUserByUsernameAndPassword(
                userLoginBindingModel.getUsername(),
                userLoginBindingModel.getPassword()
        );
        if(userServiceModel == null) {
            redirectAttributes.addFlashAttribute("wrongCredentials", true);
            return "redirect:/users/login";
        }
        httpSession.setAttribute("userServiceModel", userServiceModel);
        httpSession.setAttribute("role", userServiceModel.getRole().getRole().name());
        redirectAttributes.addFlashAttribute("kur", "KUR");

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
