package softuni.judge_v2.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.judge_v2.services.AuthService;

import javax.servlet.http.HttpSession;

@Service
public class AuthServiceImpl implements AuthService {

    private final HttpSession httpSession;

    @Autowired
    public AuthServiceImpl(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Override
    public boolean haveSession() {
        return httpSession.getAttribute("userServiceModel") != null;
    }
}
