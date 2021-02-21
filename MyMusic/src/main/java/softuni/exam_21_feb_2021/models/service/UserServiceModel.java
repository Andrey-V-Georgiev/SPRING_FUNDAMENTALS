package softuni.exam_21_feb_2021.models.service;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserServiceModel extends BaseServiceModel {

    private String username;
    private String fullName;
    private String email;
    private String password;

    public UserServiceModel() {
    }

    public UserServiceModel(String username, String fullName, String email, String password) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    @NotBlank
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 symbols")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank
    @Size(min = 3, max = 20, message = "FullName must be between 3 and 20 symbols")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @NotBlank
    @Email(message = "Must enter valid email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank
    @Size(min = 5, max = 20, message = "Password must be between 5 and 20 symbols")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
