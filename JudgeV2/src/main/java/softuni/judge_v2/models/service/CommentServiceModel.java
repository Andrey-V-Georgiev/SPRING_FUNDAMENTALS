package softuni.judge_v2.models.service;

public class CommentServiceModel {

    private Integer score;
    private String textContent;
    private UserServiceModel author;
    private HomeworkServiceModel homework;

    public CommentServiceModel() {
    }

    public CommentServiceModel(Integer score, String textContent, UserServiceModel author, HomeworkServiceModel homework) {
        this.score = score;
        this.textContent = textContent;
        this.author = author;
        this.homework = homework;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public UserServiceModel getAuthor() {
        return author;
    }

    public void setAuthor(UserServiceModel author) {
        this.author = author;
    }

    public HomeworkServiceModel getHomework() {
        return homework;
    }

    public void setHomework(HomeworkServiceModel homework) {
        this.homework = homework;
    }
}
