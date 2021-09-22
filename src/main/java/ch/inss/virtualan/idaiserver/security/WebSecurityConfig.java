package ch.inss.virtualan.idaiserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${ch.inss.idaiserver.http.auth-token-header-name}")
    private String principalRequestHeader;

    @Value("${ch.inss.idaiserver.http.auth-token}")
    private String principalRequestValue;

    @Autowired
    private Filter filter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.csrf().disable().cors(); //disable cors restriction
        http.headers().frameOptions().sameOrigin();
//        http.addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
//        http.antMatcher("/**").authorizeRequests().anyRequest().permitAll();
        http.httpBasic();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);

    }

}
