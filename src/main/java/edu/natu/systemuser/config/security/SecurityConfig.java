package edu.natu.systemuser.config.security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Ljiahai
 * @des 认证与授权安全配置
 * @date 2021-09-16 16:40:09
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }

    /**
     * 注册拦截器
     */

    @Bean
    protected RefererInterceptor getRefererInterceptor() {
        return new RefererInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加referer拦截
        registry.addInterceptor(getRefererInterceptor());
        //添加权限拦截器
        registry.addInterceptor(new AuthenticationInterceptor());
    }
}
