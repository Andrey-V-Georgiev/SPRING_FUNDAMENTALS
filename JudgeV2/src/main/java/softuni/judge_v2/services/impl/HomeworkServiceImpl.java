package softuni.judge_v2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.judge_v2.models.binding.HomeworkAddBindingModel;
import softuni.judge_v2.models.entity.Homework;
import softuni.judge_v2.models.service.ExerciseServiceModel;
import softuni.judge_v2.models.service.HomeworkServiceModel;
import softuni.judge_v2.models.service.UserServiceModel;
import softuni.judge_v2.repositories.HomeworkRepository;
import softuni.judge_v2.services.ExerciseService;
import softuni.judge_v2.services.HomeworkService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final ModelMapper modelMapper;
    private final ExerciseService exerciseService;
    private HomeworkRepository homeworkRepository;

    @Autowired
    public HomeworkServiceImpl(ModelMapper modelMapper, ExerciseService exerciseService, HomeworkRepository homeworkRepository) {
        this.modelMapper = modelMapper;
        this.exerciseService = exerciseService;
        this.homeworkRepository = homeworkRepository;
    }

    @Override
    public HomeworkServiceModel addHomework(
            HomeworkAddBindingModel homeworkAddBindingModel,
            HttpSession httpSession
    ) {
        ExerciseServiceModel exerciseServiceModel = this.exerciseService.findExerciseByName(
                homeworkAddBindingModel.getExercise()
        );
        HomeworkServiceModel homeworkServiceModel = this.modelMapper.map(
                homeworkAddBindingModel,
                HomeworkServiceModel.class
        );
        /* Set missing properties */
        homeworkServiceModel.setAddedOn(LocalDateTime.now());
        homeworkServiceModel.setAuthor((UserServiceModel) httpSession.getAttribute("userServiceModel"));
        homeworkServiceModel.setExercise(exerciseServiceModel);
        /* Save to DB */
        Homework savedHomework = this.homeworkRepository.saveAndFlush(
                this.modelMapper.map(homeworkServiceModel, Homework.class)
        );
        return this.modelMapper.map(savedHomework, HomeworkServiceModel.class);
    }

    @Override
    public HomeworkServiceModel findHomeworkByLowestComments() {
        Homework homework = this.homeworkRepository.findHomeworkByLowestComments().get(0);
        HomeworkServiceModel homeworkServiceModel = this.modelMapper.map(homework ,   HomeworkServiceModel.class  );
        return homeworkServiceModel;
    }

    @Override
    public HomeworkServiceModel findHomeworkById(String id) {
        Homework homework = this.homeworkRepository.findById(id).orElse(null);
        return this.modelMapper.map(homework, HomeworkServiceModel.class);
    }

    @Override
    public List<HomeworkServiceModel> findHomeworkByAuthorId(String authorId) {
        List<HomeworkServiceModel> homeworkServiceModels = this.homeworkRepository
                .findHomeworkByAuthorId(authorId)
                .stream()
                .map(h -> this.modelMapper.map(h, HomeworkServiceModel.class))
                .collect(Collectors.toList());
        return homeworkServiceModels;
    }
}
