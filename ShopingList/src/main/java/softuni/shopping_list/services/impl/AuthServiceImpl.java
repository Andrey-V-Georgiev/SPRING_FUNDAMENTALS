package softuni.shopping_list.services.impl;

import org.springframework.stereotype.Service;
import softuni.shopping_list.services.AuthService;

import javax.servlet.http.HttpSession;

@Service
public class AuthServiceImpl implements AuthService {

    /* ------ Validate session ------ */
    @Override
    public boolean haveSession(HttpSession httpSession) {
        return httpSession.getAttribute("userServiceModel") != null;
    }

    /* ------ Validate role ------ */
    @Override
    public boolean matchRole(String role, HttpSession httpSession) {
        String userRole = (String) httpSession.getAttribute("role");
        return userRole.equals(role);
    }
}
