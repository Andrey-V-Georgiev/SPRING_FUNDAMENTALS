package softuni.judge_v2.models.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CommentAddBindingModel {

    private Integer score;
    private String textContent;
    private String homeworkId;

    public CommentAddBindingModel() {
    }

    public CommentAddBindingModel(Integer score, String textContent, String homeworkId) {
        this.score = score;
        this.textContent = textContent;
        this.homeworkId = homeworkId;
    }

    @NotNull(message = "Must select score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Length(min = 10, message = "Must give at least 10 chars description")
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    @NotNull
    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }
}
