package softuni.judge_v2.models.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private String git;
    private Role role;

    public User() {
    }

    public User(String username, String password, String email, String git, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.git = git;
        this.role = role;
    }

    @Column(name = "username", nullable = false)
    @Length(min = 2, message = "username length must be minimum two characters!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    @Length(min = 2, message = "password length must be minimum two characters!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email")
    @Email(message = "email must contains '@'")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Pattern(regexp = "https:\\/\\/github.com\\/.*\\/SpringTestData\\/.*",
            message = "git must be a valid github address in pattern: https:/github.com/{username}/SpringTestData/…")
    @Column(name = "git")
    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }

    @ManyToOne
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
