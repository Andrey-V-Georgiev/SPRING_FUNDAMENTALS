package softuni.car_shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import softuni.car_shop.web.interceptors.NavMenuInterceptor;

@Configuration
public class AppWebConfig implements WebMvcConfigurer {

    private final NavMenuInterceptor navMenuInterceptor;

    @Autowired
    public AppWebConfig(NavMenuInterceptor navMenuInterceptor) {
        this.navMenuInterceptor = navMenuInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(navMenuInterceptor);
    }
}
