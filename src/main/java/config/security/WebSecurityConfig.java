package main.java.config.security;

import main.java.config.filter.EncodingFilter;
import main.java.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@ComponentScan({"main.java.config","main.java.service"})
@EnableWebSecurity
public class  WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(new EncodingFilter(), ChannelProcessingFilter.class);

        http.csrf().disable()
                .addFilterBefore(new EncodingFilter(), ChannelProcessingFilter.class)
                .authorizeRequests()
                .antMatchers("/**/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST,"/**/admin/**").access("hasRole('ROLE_ADMIN')")
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

        @Autowired
        protected BCryptPasswordEncoder bCryptPasswordEncoder;

        @Autowired
        protected UserServiceImpl userDetailsService;

            @Override
            public void init(AuthenticationManagerBuilder auth) throws Exception {
                PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

                auth.inMemoryAuthentication().withUser("user").password(encoder.encode("user123")).roles("USER");
                auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("admin123")).roles("ADMIN");

                auth.userDetailsService(this.userDetailsService).passwordEncoder(this.bCryptPasswordEncoder);
            }
    }
}