package softuni.shopping_list.services;

import javax.servlet.http.HttpSession;

public interface AuthService {

    boolean haveSession(HttpSession httpSession);

    boolean matchRole(String role, HttpSession httpSession);
}
