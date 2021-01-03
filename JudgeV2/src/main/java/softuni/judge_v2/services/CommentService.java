package softuni.judge_v2.services;

import softuni.judge_v2.models.binding.CommentAddBindingModel;
import softuni.judge_v2.models.service.CommentServiceModel;

import javax.servlet.http.HttpSession;

public interface CommentService {

    CommentServiceModel addComment(CommentAddBindingModel commentAddBindingModel, HttpSession httpSession);
}
