package softuni.judge_v2.services;

import softuni.judge_v2.models.service.ExerciseServiceModel;

import java.util.List;

public interface ExerciseService {
    List<ExerciseServiceModel> findAllExercises();

    ExerciseServiceModel findExerciseByName(String name);
}
