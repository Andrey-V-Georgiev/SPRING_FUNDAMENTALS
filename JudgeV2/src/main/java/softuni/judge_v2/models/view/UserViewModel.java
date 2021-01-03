package softuni.judge_v2.models.view;

import softuni.judge_v2.models.service.BaseServiceModel;

public class UserViewModel extends BaseServiceModel {

    private String username;
    private String allSendHomeworks;
    private String email;
    private String git;

    public UserViewModel() {
    }

    public UserViewModel(String username, String allSendHomeworks, String email, String git) {
        this.username = username;
        this.allSendHomeworks = allSendHomeworks;
        this.email = email;
        this.git = git;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAllSendHomeworks() {
        return allSendHomeworks;
    }

    public void setAllSendHomeworks(String allSendHomeworks) {
        this.allSendHomeworks = allSendHomeworks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }
}
