package softuni.judge_v2.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

    @PostMapping("/check")
    public String checkHomework() {


        return null;
    }

    @PostMapping("/add")
    public String addHomework() {


        return null;
    }
}
