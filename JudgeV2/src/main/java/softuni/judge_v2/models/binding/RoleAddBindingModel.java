package softuni.judge_v2.models.binding;

import org.hibernate.validator.constraints.Length;
import softuni.judge_v2.constants.GlobalConstants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RoleAddBindingModel {

    private String username;
    private String role;

    public RoleAddBindingModel() {
    }

    public RoleAddBindingModel(String username, String role) {
        this.username = username;
        this.role = role;
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
    @Pattern(regexp = "^ADMIN$|^USER$", message = "role must be ADMIN or USER")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
