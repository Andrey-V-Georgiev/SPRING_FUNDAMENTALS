package softuni.car_shop.models.binding_dtos;

import org.hibernate.validator.constraints.Length;

public class UserRegisterBindingModel {

    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public UserRegisterBindingModel() {
    }

    public UserRegisterBindingModel(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    @Length(min = 3, max = 50, message = "First name must be between 3 and 50 symbols")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Length(min = 3, max = 50, message = "Last name must be between 3 and 50 symbols")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Length(min = 3, max = 20, message = "Username must be between 3 and 20 symbols")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 3, max = 20 , message = "Password must be between 3 and 20 symbols")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
