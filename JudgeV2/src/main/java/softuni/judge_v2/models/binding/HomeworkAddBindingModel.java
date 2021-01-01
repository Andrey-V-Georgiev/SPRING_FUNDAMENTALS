package softuni.judge_v2.models.binding;


import softuni.judge_v2.models.service.ExerciseServiceModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class HomeworkAddBindingModel {

    private String gitAddress;
    private String exercise;

    public HomeworkAddBindingModel() {
    }

    public HomeworkAddBindingModel(String gitAddress, String exercise) {
        this.gitAddress = gitAddress;
        this.exercise = exercise;
    }

    @NotNull
    @Pattern(regexp = "https:\\/\\/github.com\\/.*\\/SpringTestData\\/.*",
            message = "git must be a valid github address in pattern: https://github.com/{username}/SpringTestData/…")
    public String getGitAddress() {
        return gitAddress;
    }

    public void setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
    }

    @NotNull(message = "Must select exercise")
    @Pattern(regexp = "^((?!Select exercise).*)*$", message = "Must select exercise, different than default")
    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }
}
