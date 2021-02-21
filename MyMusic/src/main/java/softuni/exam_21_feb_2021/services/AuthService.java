package softuni.exam_21_feb_2021.services;

import javax.servlet.http.HttpSession;

public interface AuthService {

    boolean haveSession(HttpSession httpSession);
}
