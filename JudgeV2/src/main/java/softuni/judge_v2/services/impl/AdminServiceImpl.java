package softuni.judge_v2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.judge_v2.models.entity.Exercise;
import softuni.judge_v2.models.service.ExerciseServiceModel;
import softuni.judge_v2.repositories.ExerciseRepository;
import softuni.judge_v2.services.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    private final ModelMapper modelMapper;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public AdminServiceImpl(ModelMapper modelMapper, ExerciseRepository exerciseRepository) {
        this.modelMapper = modelMapper;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public ExerciseServiceModel exerciseAdd(ExerciseServiceModel exerciseServiceModel) {
        Exercise exercise = this.modelMapper.map(exerciseServiceModel, Exercise.class);
        Exercise exerciseSaved = this.exerciseRepository.saveAndFlush(exercise);
        return this.modelMapper.map(exerciseSaved, ExerciseServiceModel.class);
    }
}
