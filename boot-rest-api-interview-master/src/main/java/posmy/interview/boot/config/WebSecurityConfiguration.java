package posmy.interview.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import posmy.interview.boot.enumeration.UserRoles;

/**
 * @author Rashidi Zin
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
    private CustomAuthenticationProvider authProvider;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/users").hasAnyAuthority(UserRoles.LIBRARIAN.toString())
            .antMatchers(HttpMethod.GET, "/users").hasAnyAuthority(UserRoles.LIBRARIAN.toString(), UserRoles.MEMBER.toString())
            .antMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority(UserRoles.LIBRARIAN.toString(), UserRoles.MEMBER.toString())
            .antMatchers(HttpMethod.PUT, "/users/**").hasAnyAuthority(UserRoles.LIBRARIAN.toString())
            .antMatchers(HttpMethod.DELETE, "/users").hasAnyAuthority(UserRoles.LIBRARIAN.toString())
            .antMatchers(HttpMethod.DELETE, "/users/**").hasAnyAuthority(UserRoles.LIBRARIAN.toString(), UserRoles.MEMBER.toString())
            .antMatchers(HttpMethod.POST, "/books").hasAnyAuthority(UserRoles.LIBRARIAN.toString())
            .antMatchers(HttpMethod.GET, "/books").hasAnyAuthority(UserRoles.LIBRARIAN.toString(), UserRoles.MEMBER.toString())
            .antMatchers(HttpMethod.GET, "/books/**").hasAnyAuthority(UserRoles.LIBRARIAN.toString(), UserRoles.MEMBER.toString())
            .antMatchers(HttpMethod.PUT, "/books/borrow/**").hasAnyAuthority(UserRoles.MEMBER.toString())
            .antMatchers(HttpMethod.PUT, "/books/return/**").hasAnyAuthority(UserRoles.MEMBER.toString())
            .antMatchers(HttpMethod.PUT, "/books/**").hasAnyAuthority(UserRoles.LIBRARIAN.toString())
            .antMatchers(HttpMethod.DELETE, "/books/**").hasAnyAuthority(UserRoles.LIBRARIAN.toString())
            .and()
            .csrf().disable()
            .formLogin().disable();
    }


}
