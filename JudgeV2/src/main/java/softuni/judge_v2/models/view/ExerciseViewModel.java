package softuni.judge_v2.models.view;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import softuni.judge_v2.models.service.BaseServiceModel;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

public class ExerciseViewModel extends BaseServiceModel {

    private String name;
    private LocalDateTime startedOn;
    private LocalDateTime dueDate;

    public ExerciseViewModel() {
    }

    public ExerciseViewModel(String name, LocalDateTime startedOn, LocalDateTime dueDate) {
        this.name = name;
        this.startedOn = startedOn;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
