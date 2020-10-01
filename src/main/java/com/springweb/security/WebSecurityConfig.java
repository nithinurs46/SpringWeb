package com.springweb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.springweb.exception.MyAccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	//@Qualifier("myUserDetailsService")
	@Autowired
	private UserDetailsService userDetailsService;


	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/*.js/**","/*.css/**", "/registerUser", "/saveUserRegistration").permitAll()
                .antMatchers("/deleteUser/**").access("hasAuthority('DELETE_USER_PRIVILEGE')") 
                .antMatchers("/editUser/**").access("hasAuthority('EDIT_USER_PRIVILEGE')") 
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("userId")
                .permitAll()
                .defaultSuccessUrl("/displayHomePage")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
                .logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true) 
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .accessDeniedHandler(accessDeniedHandler());
    }

	@Bean
	public PasswordEncoder getpasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(getpasswordEncoder());
	    return authProvider;
	}
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
	    return new MyAccessDeniedHandler();
	}
	
	
	/*@Override
	public void configure(WebSecurity web) throws Exception {
	    //Web resources
	    web.ignoring().antMatchers("/css/**");
	    web.ignoring().antMatchers("/scripts/**");
	    web.ignoring().antMatchers("/js/**");
	    web.ignoring().antMatchers("/images/**");
	}*/
	
}
