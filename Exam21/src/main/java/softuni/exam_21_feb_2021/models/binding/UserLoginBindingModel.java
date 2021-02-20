package softuni.exam_21_feb_2021.models.binding;

import org.hibernate.validator.constraints.Length;

public class UserLoginBindingModel {

    private String username;
    private String password;

    public UserLoginBindingModel() {
    }

    public UserLoginBindingModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Length(min = 1, max = 1, message = "Username must be between _ and _ symbols")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 1, max = 1, message = "Password must be between _ and _ symbols")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
