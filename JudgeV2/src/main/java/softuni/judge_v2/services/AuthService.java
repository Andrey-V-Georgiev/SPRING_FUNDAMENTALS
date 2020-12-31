package softuni.judge_v2.services;

import javax.servlet.http.HttpSession;

public interface AuthService {

    boolean haveSession(HttpSession httpSession);

    boolean matchRole(String role, HttpSession httpSession);
}
