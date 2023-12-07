package hello.login;

import hello.login.web.argumentresolver.LoginMemberArgumentResolver;
import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import hello.login.web.interceptor.LogInterceptor;
import hello.login.web.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.Filter;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {            //WebMvcConfigurer은 인터셉터를 위한 인터페이스 상속


    //LoginMemberArgumentResolver 등록
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }

    //인터셉터 등록
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**" , "/*.ico", "/error");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/members/add", "/login", "/logout",
                        "/css/**", "/*.ico", "/error");
    }

    //@Bean // 인터셉터 등록으로 인한 주석처리
    public FilterRegistrationBean logFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistration = new FilterRegistrationBean<>();
        filterFilterRegistration.setFilter(new LogFilter());
        filterFilterRegistration.setOrder(1);
        filterFilterRegistration.addUrlPatterns("/*");

        return filterFilterRegistration;
    }

    //@Bean // 인터셉터 등록으로 인한 주석처리
    public FilterRegistrationBean loginCheckFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistration = new FilterRegistrationBean<>();
        filterFilterRegistration.setFilter(new LoginCheckFilter());
        filterFilterRegistration.setOrder(2);
        filterFilterRegistration.addUrlPatterns("/*");

        return filterFilterRegistration;
    }
}
