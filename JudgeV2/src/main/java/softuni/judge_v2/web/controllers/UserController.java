package softuni.judge_v2.web.controllers;

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

import softuni.judge_v2.models.binding.UserLoginBindingModel;
import softuni.judge_v2.models.binding.UserRegisterBindingModel;
import softuni.judge_v2.models.service.UserServiceModel;
import softuni.judge_v2.services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
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
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:/user/register";
        }
        /* Validate password and confirmPassword match */
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("passwordMismatch", true);
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:/user/register";
        }
        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);
        return "redirect:/user/login";
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
            HttpSession httpSession,
            RedirectAttributes redirectAttributes
    ) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:/user/login";
        } else {
            UserServiceModel userServiceModel = this.userService.findByUsername(userLoginBindingModel.getUsername());

            if (userServiceModel == null) {
                redirectAttributes.addFlashAttribute("incorrectUsername", true);
                redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
                return "redirect:/user/login";
            }

            if (!userServiceModel.getPassword().equals(userLoginBindingModel.getPassword())) {
                redirectAttributes.addFlashAttribute("wrongPassword", true);
                redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
                return "redirect:/user/login";
            }

            httpSession.setAttribute("userServiceModel", userServiceModel);
            httpSession.setAttribute("id", userServiceModel.getId());
            httpSession.setAttribute("role", userServiceModel.getRole().getName());
            return "redirect:/home";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
