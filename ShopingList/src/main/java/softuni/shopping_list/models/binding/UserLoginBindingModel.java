package softuni.shopping_list.models.binding;

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
}
