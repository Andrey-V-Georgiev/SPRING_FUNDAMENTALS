package softuni.shopping_list.models.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

public class UserRegisterBindingModel {

    private String username;
    private String password;
    private String email;
    private String confirmPassword;


    public UserRegisterBindingModel() {
    }

    public UserRegisterBindingModel(String username, String password, String email, String confirmPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @Length(min = 3, max = 20, message = "Username must be between 3 an 20 symbols")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Email(message = "Must enter valid email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 3, max = 20, message = "Password must be between 3 an 20 symbols")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Length(min = 3, max = 20, message = "ConfirmedPassword must be between 3 an 20 symbols")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
