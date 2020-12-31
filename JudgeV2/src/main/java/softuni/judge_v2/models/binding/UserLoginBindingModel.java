package softuni.judge_v2.models.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserLoginBindingModel {

    private String username;
    private String password;

    public UserLoginBindingModel() {
    }

    public UserLoginBindingModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @NotNull
    @Length(min = 2, message = "username length must be minimum two characters!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Length(min = 2, message = "password length must be minimum two characters!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
