package softuni.exam_21_feb_2021.models.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginBindingModel {

    private String username;
    private String password;

    public UserLoginBindingModel() {
    }

    public UserLoginBindingModel(String username, String password) {
        this.username = username;
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
    @Size(min = 5, max = 20, message = "Password must be between 5 and 20 symbols")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
