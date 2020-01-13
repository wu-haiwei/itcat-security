package com.itcat.security.config;

import com.itcat.security.entity.CustomAuthenticationDetailsSource;
import com.itcat.security.handler.LoginFailureHandler;
import com.itcat.security.handler.LoginSuccessHandler;
import com.itcat.security.handler.WebAccessDeniedHandler;
import com.itcat.security.handler.WebAuthenticationFailureHandler;
import com.itcat.security.handler.WebLoginAuthenticationEntryPoint;
import com.itcat.security.validate.LoginValidateAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic()
                .and()
                    .authorizeRequests()
                    //必须经过认证以后才能访问
                    .anyRequest().access("@rbacService.hasPermission(request,authentication)")
                .and()
                    .formLogin()
                    .successHandler(loginSuccessHandler())
                    .failureHandler(loginFailureHandler())
                    .authenticationDetailsSource(authenticationDetailsSource())
                    .permitAll()
                .and()
                    .sessionManagement()
                    .maximumSessions(1);

        // 注销配置
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true);

        //关闭csrf跨域攻击防御
        http.csrf().disable();

        // 登录认证失败 返回JSON数据
        // http.formLogin().failureHandler(webAuthenticationFailureHandler()).permitAll();

        // 访问鉴权失败 返回JSON数据
        http.exceptionHandling().accessDeniedHandler(webAccessDeniedHandler());

        // 自定义未登录返回 JSON数据
        http.exceptionHandling().authenticationEntryPoint(webLoginAuthenticationEntryPoint());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //这里要设置自定义认证
        auth.authenticationProvider(loginValidateAuthenticationProvider());
    }

    @Bean
    public CustomAuthenticationDetailsSource authenticationDetailsSource(){
        return new CustomAuthenticationDetailsSource();
    }

    @Bean
    public LoginFailureHandler loginFailureHandler(){
        return new LoginFailureHandler();
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }

    @Bean
    public LoginValidateAuthenticationProvider loginValidateAuthenticationProvider(){
        return new LoginValidateAuthenticationProvider();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebAccessDeniedHandler webAccessDeniedHandler() {
        return new WebAccessDeniedHandler();
    }

    @Bean
    public WebAuthenticationFailureHandler webAuthenticationFailureHandler() {
        return new WebAuthenticationFailureHandler();
    }

    @Bean
    public AuthenticationEntryPoint webLoginAuthenticationEntryPoint() {
        return new WebLoginAuthenticationEntryPoint();
    }
}
