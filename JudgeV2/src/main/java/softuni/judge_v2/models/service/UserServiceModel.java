package softuni.judge_v2.models.service;

import org.hibernate.validator.constraints.Length;
import softuni.judge_v2.models.entity.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserServiceModel extends BaseServiceModel {

    private String username;
    private String password;
    private String email;
    private String git;
    private RoleServiceModel roleServiceModel;

    public UserServiceModel() {
    }

    public UserServiceModel(String username, String password, String email, String git, RoleServiceModel roleServiceModel) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.git = git;
        this.roleServiceModel = roleServiceModel;
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

    @NotNull
    @Email(message = "email must contains '@'")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    @Pattern(regexp = "https:\\/\\/github.com\\/.*\\/SpringTestData\\/.*",
            message = "git must be a valid github address in pattern: https://github.com/{username}/SpringTestData/…")
    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }

    public RoleServiceModel getRole() {
        return roleServiceModel;
    }

    public void setRole(RoleServiceModel role) {
        this.roleServiceModel = role;
    }
}
