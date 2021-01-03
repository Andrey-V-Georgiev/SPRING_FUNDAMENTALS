package softuni.judge_v2.models.entity;


import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "homeworks")
public class Homework extends BaseEntity {

    private LocalDateTime addedOn;
    private String gitAddress;
    private User author;
    private Exercise exercise;
    private Set<Comment> comments;

    public Homework() {
    }

    public Homework(LocalDateTime addedOn, String gitAddress, User author, Exercise exercise) {
        this.addedOn = addedOn;
        this.gitAddress = gitAddress;
        this.author = author;
        this.exercise = exercise;
    }

    @Column(name = "added_on", nullable = false, unique = true)
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    @Pattern(regexp = "https:\\/\\/github.com\\/.*\\/SpringTestData\\/.*",
            message = "git must be a valid github address in pattern: https:/github.com/{username}/SpringTestData/…")
    @Column(name = "git_address")
    public String getGitAddress() {
        return gitAddress;
    }

    public void setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
    }

    @ManyToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne
    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    @OneToMany
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
