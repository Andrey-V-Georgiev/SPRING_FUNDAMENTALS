package softuni.judge_v2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.judge_v2.models.binding.CommentAddBindingModel;
import softuni.judge_v2.models.entity.Comment;
import softuni.judge_v2.models.service.CommentServiceModel;
import softuni.judge_v2.models.service.UserServiceModel;
import softuni.judge_v2.repositories.CommentRepository;
import softuni.judge_v2.services.CommentService;
import softuni.judge_v2.services.HomeworkService;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;
    private final HomeworkService homeworkService;

    @Autowired
    public CommentServiceImpl(ModelMapper modelMapper, CommentRepository commentRepository, HomeworkService homeworkService) {
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
        this.homeworkService = homeworkService;
    }

    @Override
    public CommentServiceModel addComment(CommentAddBindingModel commentAddBindingModel, HttpSession httpSession) {

        CommentServiceModel commentServiceModel = this.modelMapper.map(commentAddBindingModel, CommentServiceModel.class);
        commentServiceModel.setAuthor((UserServiceModel) httpSession.getAttribute("userServiceModel"));
        commentServiceModel.setHomework(this.homeworkService.findHomeworkById(commentAddBindingModel.getHomeworkId()));

        Comment savedComment = this.commentRepository.saveAndFlush(this.modelMapper.map(commentServiceModel, Comment.class));

        return this.modelMapper.map(savedComment, CommentServiceModel.class);
    }
}
