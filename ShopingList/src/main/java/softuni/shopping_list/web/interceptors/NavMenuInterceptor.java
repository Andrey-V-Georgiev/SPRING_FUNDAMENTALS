package softuni.shopping_list.web.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class NavMenuInterceptor implements HandlerInterceptor {

    @Autowired
    private HttpSession httpSession;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (httpSession.getAttribute("userServiceModel") == null) {
            httpSession.setAttribute("haveSession", 0);
        } else {
            httpSession.setAttribute("haveSession", 1);
        }
        return true;
    }
}
