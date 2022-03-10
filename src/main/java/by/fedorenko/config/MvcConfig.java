package by.fedorenko.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("/auth/login");
        registry.addViewController("/telegram-page").setViewName("telegram-page");
        registry.addViewController("/user").setViewName("user-page");
        registry.addViewController("/success").setViewName("/auth/success");
        registry.addViewController("/registration").setViewName("/auth/registration");
        registry.addViewController("/403").setViewName("/error/403");
        registry.addViewController("/error").setViewName("/error/error");
        registry.addViewController("/404").setViewName("/error/404");


    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

}