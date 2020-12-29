package softuni.judge_v2.models.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
@Table(name = "exercises")
public class Exercise extends BaseEntity {

    private String name;
    private LocalDateTime startedOn;
    private LocalDateTime dueDate;

    public Exercise() {
    }

    public Exercise(String name, LocalDateTime startedOn, LocalDateTime dueDate) {
        this.name = name;
        this.startedOn = startedOn;
        this.dueDate = dueDate;
    }

    @Column(name = "name")
    @Length(min = 2, message = "name length must be minimum two characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @PastOrPresent(message = "date cannot be in the future")
    @Column(name = "started_on")
    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "date cannot be in the past")
    @Column(name = "due_date")
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
