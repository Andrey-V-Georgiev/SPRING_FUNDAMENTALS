package softuni.shopping_list.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import softuni.shopping_list.web.interceptors.NavFragmentInterceptor;

@Configuration
public class ApplicationWebConfiguration implements WebMvcConfigurer {

    private final NavFragmentInterceptor navFragmentInterceptor;

    @Autowired
    public ApplicationWebConfiguration(NavFragmentInterceptor navFragmentInterceptor) {
        this.navFragmentInterceptor = navFragmentInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(navFragmentInterceptor);
    }
}
