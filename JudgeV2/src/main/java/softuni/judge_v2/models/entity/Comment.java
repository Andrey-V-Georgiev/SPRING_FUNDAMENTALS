package softuni.judge_v2.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    private Integer score;
    private String textContent;
    private User author;
    private Homework homework;

    public Comment() {
    }

    public Comment(Integer score, String textContent, User author, Homework homework) {
        this.score = score;
        this.textContent = textContent;
        this.author = author;
        this.homework = homework;
    }

    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Column(name = "text_content", columnDefinition = "TEXT")
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    @ManyToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }
}
