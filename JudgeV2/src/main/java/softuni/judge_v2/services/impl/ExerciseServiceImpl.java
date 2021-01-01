package softuni.judge_v2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.judge_v2.models.entity.Exercise;
import softuni.judge_v2.models.service.ExerciseServiceModel;
import softuni.judge_v2.repositories.ExerciseRepository;
import softuni.judge_v2.services.ExerciseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ExerciseServiceModel> findAllExercises() {
        List<ExerciseServiceModel> exerciseServiceModels = this.exerciseRepository.findAll()
                .stream()
                .map(e -> this.modelMapper.map(e, ExerciseServiceModel.class))
                .collect(Collectors.toList());
        return exerciseServiceModels;
    }

    @Override
    public ExerciseServiceModel findExerciseByName(String name) {
        ExerciseServiceModel exerciseServiceModel = this.exerciseRepository
                .findExerciseByName(name)
                .map(ex -> this.modelMapper.map(ex, ExerciseServiceModel.class))
                .orElse(null);
        return exerciseServiceModel;
    }
}
