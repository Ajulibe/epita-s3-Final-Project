package com.ajulibe.java.SpringBootApi.security.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/*
 *Setting a login view for the app
 * */

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login.html").setViewName("login");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {

        registry.addViewController("/anonymous.html");
        registry.addViewController("/login.html");
        registry.addViewController("/homepage.html");
        registry.addViewController("/admin/adminpage.html");
        registry.addViewController("/accessDenied");
    }

    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver bean = new InternalResourceViewResolver();

        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/view/");
        bean.setSuffix(".jsp");

        return bean;
    }
}
