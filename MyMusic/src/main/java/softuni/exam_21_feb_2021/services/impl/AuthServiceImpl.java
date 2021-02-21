package softuni.exam_21_feb_2021.services.impl;

import org.springframework.stereotype.Service;
import softuni.exam_21_feb_2021.services.AuthService;

import javax.servlet.http.HttpSession;

@Service
public class AuthServiceImpl implements AuthService {

    /* ------ Validate session ------ */
    @Override
    public boolean haveSession(HttpSession httpSession) {
        return httpSession.getAttribute("userServiceModel") != null;
    }
}
