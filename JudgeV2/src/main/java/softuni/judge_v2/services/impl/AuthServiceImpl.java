package softuni.judge_v2.services.impl;

import org.springframework.stereotype.Service;
import softuni.judge_v2.services.AuthService;

import javax.servlet.http.HttpSession;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public boolean haveSession(HttpSession httpSession) {
        return httpSession.getAttribute("userServiceModel") != null;
    }

    @Override
    public boolean matchRole(String role, HttpSession httpSession) {
        String userRole = (String) httpSession.getAttribute("role");
        return userRole.equals(role);
    }
}
