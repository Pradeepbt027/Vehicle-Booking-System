package com.eikona.tech.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.eikona.tech.service.impl.MyUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	

	@Configuration
	public static class WebConfiguration extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private MyUserDetailsService myUserDetailsService;
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth)throws Exception{
			auth.authenticationProvider(authenticationProvider());
		}
		
		@Bean
	    DaoAuthenticationProvider authenticationProvider(){
	        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	        daoAuthenticationProvider.setUserDetailsService(this.myUserDetailsService);

	        return daoAuthenticationProvider;
	    }

		 @Override
		 protected void configure(HttpSecurity http) throws Exception {
 				  
			 http
			 .antMatcher("/**")
			 .csrf().disable()
			 .authorizeRequests()
              			
			 .antMatchers(HttpMethod.POST, "/LAPI/**" ,"/Subscribe/**","/hfsecurity/**").permitAll()
			 .antMatchers("/resources/**", "/webjars/**","/assets/**","/api/**").permitAll()
			 .anyRequest().authenticated()
		    
			 .and()
			 .formLogin().loginPage("/login")
			 .defaultSuccessUrl("/")
			 .failureUrl("/login?error").permitAll()
		     
			 .and()
			 .logout()
			 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			 .logoutSuccessUrl("/login?logout") .permitAll()
		     
			 .and()
			 .exceptionHandling().accessDeniedPage("/403");
		 }
	}
}


