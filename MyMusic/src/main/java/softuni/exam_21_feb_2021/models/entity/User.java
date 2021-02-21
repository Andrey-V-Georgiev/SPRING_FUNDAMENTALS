package softuni.exam_21_feb_2021.models.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private String fullName;
    private String password;
    private String email;

    public User() {
    }

    public User(String username, String fullName, String password, String email) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
    }

    @NotBlank
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 symbols")
    @Column(name = "username", unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank
    @Size(min = 3, max = 20, message = "FullName must be between 3 and 20 symbols")
    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @NotBlank
    @Size(min = 5, max = 20, message = "Password must be between 5 and 20 symbols")
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank
    @Email(message = "Must enter valid email")
    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
