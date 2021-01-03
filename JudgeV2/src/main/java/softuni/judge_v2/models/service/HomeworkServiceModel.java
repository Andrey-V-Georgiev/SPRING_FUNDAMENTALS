package softuni.judge_v2.models.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class HomeworkServiceModel extends BaseServiceModel {

    private LocalDateTime addedOn;
    private String gitAddress;
    private UserServiceModel author;
    private ExerciseServiceModel exercise;

    public HomeworkServiceModel() {
    }

    public HomeworkServiceModel(LocalDateTime addedOn, String gitAddress, UserServiceModel author, ExerciseServiceModel exercise) {
        this.addedOn = addedOn;
        this.gitAddress = gitAddress;
        this.author = author;
        this.exercise = exercise;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    @Pattern(regexp = "https:\\/\\/github.com\\/.*\\/SpringTestData\\/.*",
            message = "git must be a valid github address in pattern: https:/github.com/{username}/SpringTestData/…")
    public String getGitAddress() {
        return gitAddress;
    }

    public void setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
    }

    public UserServiceModel getAuthor() {
        return author;
    }

    public void setAuthor(UserServiceModel author) {
        this.author = author;
    }

    @NotNull
    public ExerciseServiceModel getExercise() {
        return exercise;
    }

    public void setExercise(ExerciseServiceModel exercise) {
        this.exercise = exercise;
    }
}
