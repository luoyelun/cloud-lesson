package icu.thyself.cloudlesson.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author luoyelun
 * @date 2020/5/3 19:15
 */
@EnableWebSecurity()
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    //    @Autowired
//    private MyAuthenticationFailHander myAuthenticationFailHander;
    @Qualifier("myUserDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Autowired
    MyAccessDeniedHandler accessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                //不做限制的请求
                .antMatchers("/", "/index/**", "/register", "/register/**", "/t/*", "/search", "/search/*", "/recommend").permitAll()
                .and()
                //登录不限制
                .formLogin().loginPage("/login").loginProcessingUrl("/login/form").failureUrl("/loginerror")
                .successHandler(myAuthenticationSuccessHandler)
//                .failureHandler(myAuthenticationFailHander)
                .permitAll()
                .and()
                //登出不限制
                .logout().logoutUrl("/logout").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/publish").hasAnyRole("AUTHOR", "ADMIN")
                .antMatchers("/u/**").hasAnyRole("USER", "AUTHOR", "ADMIN")
                .anyRequest().authenticated();
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        http.rememberMe().rememberMeParameter("rememberMe").userDetailsService(userDetailsService);
        //没有权限，跳转请求
        http.formLogin().loginPage("/login");
        //登出处理
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //放行静态资源
        web.ignoring().antMatchers(
                "/css/**",
                "/js/**",
                "/images/**",
                "/icon/**",
                "/fonts/**",
                "/favicon.ico");
    }
}
