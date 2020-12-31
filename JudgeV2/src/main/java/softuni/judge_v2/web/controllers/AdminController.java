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
import softuni.judge_v2.constants.GlobalConstants;
import softuni.judge_v2.models.binding.ExerciseAddBindingModel;
import softuni.judge_v2.models.service.ExerciseServiceModel;
import softuni.judge_v2.services.AdminService;
import softuni.judge_v2.services.AuthService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static softuni.judge_v2.constants.GlobalConstants.BINDINGRESULT_PREFIX;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AuthService authService;
    private final ModelMapper modelMapper;
    private final AdminService adminService;

    @Autowired
    public AdminController(AuthService authService, ModelMapper modelMapper, AdminService adminService) {
        this.authService = authService;
        this.modelMapper = modelMapper;
        this.adminService = adminService;
    }

    @GetMapping("/exercise-add")
    public String addExercise(Model model, HttpSession httpSession) {
        String role = (String) httpSession.getAttribute("role");
        System.out.println(role);

        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        if (!this.authService.matchRole(GlobalConstants.ADMIN_ROLE, httpSession)) {
            return "redirect:/home";
        }
        if (!model.containsAttribute("exerciseAddBindingModel")) {
            model.addAttribute("exerciseAddBindingModel", new ExerciseAddBindingModel());
        }
        return "exercise-add";
    }

    @PostMapping("/exercise-add")
    public String addExerciseConfirm(
            @Valid @ModelAttribute("exerciseAddBindingModel") ExerciseAddBindingModel exerciseAddBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            HttpSession httpSession
    ) {
        System.out.println();
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        if (!this.authService.matchRole(GlobalConstants.ADMIN_ROLE, httpSession)) {
            return "redirect:/home";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("exerciseAddBindingModel", exerciseAddBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "exerciseAddBindingModel", bindingResult);
            return "redirect:/admin/exercise-add";
        } else {
            ExerciseServiceModel exerciseServiceModel = this.modelMapper.map(
                    exerciseAddBindingModel,  ExerciseServiceModel.class
            );
            this.adminService.exerciseAdd(exerciseServiceModel);
            return "redirect:/home";
        }
    }

    @PostMapping("/add-role")
    public String addRole(HttpSession httpSession) {
        if (!this.authService.haveSession(httpSession)) {
            return "redirect:/";
        }
        if (!this.authService.matchRole(GlobalConstants.ADMIN_ROLE, httpSession)) {
            return "redirect:/home";
        }

        return "role-add";
    }
}
