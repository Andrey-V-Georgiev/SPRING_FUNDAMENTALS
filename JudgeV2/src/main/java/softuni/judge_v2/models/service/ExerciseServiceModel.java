package softuni.judge_v2.models.service;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

public class ExerciseServiceModel extends BaseServiceModel {

    private String name;
    private LocalDateTime startedOn;
    private LocalDateTime dueDate;

    public ExerciseServiceModel() {
    }

    public ExerciseServiceModel(String name, LocalDateTime startedOn, LocalDateTime dueDate) {
        this.name = name;
        this.startedOn = startedOn;
        this.dueDate = dueDate;
    }

    @NotNull
    @Length(min = 2, message = "name length must be minimum two characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @PastOrPresent(message = "date cannot be in the future")
    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
    }

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "date cannot be in the past")
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
