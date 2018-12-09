package main.java.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
////TODO
//    https://www.concretepage.com/spring-4/spring-4-security-annotation-login-example-with-gradle
//
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
//                .and()
//                .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
//                .and()
//                .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable().
//                authorizeRequests().
//                antMatchers("/register").permitAll().
//                antMatchers("/index").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')").
//                and().
//                formLogin().
//                    loginPage("/login").permitAll().
//                    loginProcessingUrl("/login").
//                    defaultSuccessUrl("/index").
//                    failureForwardUrl("/accessDenied").
//                    usernameParameter("/email").
//                    passwordParameter("/password");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

@Configuration
@ComponentScan("main.java.config")
@EnableWebSecurity
public class  WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/**/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/**/user/**").access("hasRole('ROLE_USER')") //think over
                .antMatchers("/register").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/index").permitAll()
                .and().formLogin()
                    .loginPage("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/default");
    }

    @Configuration
    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

            auth.inMemoryAuthentication().withUser("user").password(encoder.encode("user123")).roles("USER");
            auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("admin123")).roles("ADMIN");

        }
    }
}