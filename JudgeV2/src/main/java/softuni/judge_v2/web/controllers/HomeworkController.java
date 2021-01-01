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
import softuni.judge_v2.models.binding.HomeworkAddBindingModel;
import softuni.judge_v2.models.view.ExerciseViewModel;
import softuni.judge_v2.services.ExerciseService;
import softuni.judge_v2.services.HomeworkService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static softuni.judge_v2.constants.GlobalConstants.BINDINGRESULT_PREFIX;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

    private final ModelMapper modelMapper;
    private final ExerciseService exerciseService;
    private final HomeworkService homeworkService;

    @Autowired
    public HomeworkController(ModelMapper modelMapper, ExerciseService exerciseService, HomeworkService homeworkService) {
        this.modelMapper = modelMapper;
        this.exerciseService = exerciseService;
        this.homeworkService = homeworkService;
    }

    @GetMapping("/add")
    public String addHomework(Model model) {
        if (!model.containsAttribute("homeworkAddBindingModel")) {
            model.addAttribute("homeworkAddBindingModel", new HomeworkAddBindingModel());
        }
        List<ExerciseViewModel> exerciseViewModels = this.exerciseService.findAllExercises()
                .stream()
                .map(e -> this.modelMapper.map(e, ExerciseViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("allExercises", exerciseViewModels);
        return "homework-add";
    }

    @PostMapping("/add")
    public String addHomeworkConfirm(
            @Valid @ModelAttribute("homeworkAddBindingModel") HomeworkAddBindingModel homeworkAddBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            HttpSession httpSession
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("homeworkAddBindingModel", homeworkAddBindingModel);
            redirectAttributes.addFlashAttribute(BINDINGRESULT_PREFIX + "homeworkAddBindingModel", bindingResult);
            return "redirect:/homework/add";
        } else {
            this.homeworkService.addHomework(homeworkAddBindingModel, httpSession);
            return "redirect:/home";
        }
    }

    @PostMapping("/check")
    public String checkHomework() {


        return null;
    }
}
