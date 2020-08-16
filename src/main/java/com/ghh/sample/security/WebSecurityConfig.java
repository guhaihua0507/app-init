package com.ghh.sample.security;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final static Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private AppAuthenticationEntryPoint authenticationEntryPoint;

//    /**
//     * 静态资源设置
//     */
//    @Override
//    public void configure(WebSecurity webSecurity) {
//        //不拦截静态资源,所有用户均可访问的资源
//        webSecurity.ignoring().antMatchers(
//                "/",
//                "/css/**",
//                "/js/**",
//                "/images/**",
//                "/layui/**"
//        );
//    }

    /**
     * http请求设置
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/login", "/logOut").permitAll()
                .antMatchers("/**/webjars/**",
                        "/**/swagger-ui.html",
                        "/**/v2/api-docs/**",
                        "/**/csrf/**",
                        "/**/actuator/**",
                        "/**/swagger-resources/**").permitAll()
                .anyRequest().authenticated()
//                .anyRequest()
//                .access("@rbacPermission.hasPermission(request, authentication)")//根据账号权限访问
//                .and()
//                .formLogin()
//                .loginPage("/")
//                .loginPage("/login")   //登录请求页
//                .loginProcessingUrl("/login")  //登录POST请求路径
//                .usernameParameter("username") //登录用户名参数
//                .passwordParameter("password") //登录密码参数
//                .defaultSuccessUrl("/main")   //默认登录成功页面
//                .and().formLogin().loginProcessingUrl("/login").permitAll().successHandler(loginSuccessHandler())//.failureHandler()
                .and().logout().permitAll().logoutSuccessHandler(logoutSuccessHandler()).invalidateHttpSession(true)
                .and()
//                .addFilter(new MyAuthenticationProcessingFilter())
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
//                .accessDeniedHandler(customAccessDeniedHandler) //无权限处理器
                .and().csrf().disable();
        http.addFilterAt(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
        auth.authenticationProvider(usernamePasswordAuthenticationProvider());
    }

    @Bean
    public AuthenticationProvider usernamePasswordAuthenticationProvider() {
        return new UsernamePasswordAuthenticationProvider();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() { //登出处理
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                try {
                    User user = (User) authentication.getPrincipal();
                    logger.info("USER : " + user.getUsername() + " LOGOUT SUCCESS !  ");
                } catch (Exception e) {
                    logger.info("LOGOUT EXCEPTION , e : " + e.getMessage());
                }
                httpServletResponse.sendRedirect("/login");
            }
        };
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() { //登入处理
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                User userDetails = (User) authentication.getPrincipal();
//                logger.info("USER : " + userDetails.getUsername() + " LOGIN SUCCESS !  ");
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }

    @Bean
    public UsernameAuthenticationProcessingFilter myAuthenticationFilter() throws Exception {
        UsernameAuthenticationProcessingFilter filter = new UsernameAuthenticationProcessingFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(loginSuccessHandler());
        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JSON.toJSONString("登录失败!"));
            }
        });
        return filter;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
    }
}
