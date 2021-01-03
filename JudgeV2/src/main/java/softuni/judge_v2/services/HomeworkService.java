package softuni.judge_v2.services;

import softuni.judge_v2.models.binding.HomeworkAddBindingModel;
import softuni.judge_v2.models.entity.Homework;
import softuni.judge_v2.models.service.HomeworkServiceModel;

import javax.servlet.http.HttpSession;

public interface HomeworkService {
    HomeworkServiceModel addHomework(HomeworkAddBindingModel homeworkAddBindingModel, HttpSession httpSession);

    HomeworkServiceModel findHomeworkByLowestComments();

    HomeworkServiceModel findHomeworkById(String id);
}
