package softuni.shopping_list.models.service;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

public class UserServiceModel extends BaseServiceModel {

    private String username;
    private String password;
    private String email;

    public UserServiceModel() {
    }

    public UserServiceModel(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Length(min = 3, max = 20, message = "Username must be between 3 an 20 symbols")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 3, max = 20, message = "Password must be between 3 an 20 symbols")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email(message = "Must enter valid email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
