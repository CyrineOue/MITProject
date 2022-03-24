package tn.MITProject.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tn.MITProject.Service.MyLogDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyLogDetailsService logDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		auth.userDetailsService(logDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() .antMatchers("/registration").permitAll()
		                         //.antMatchers("/admin/**").permitAll()
		                         .antMatchers("/admin/**").access("hasRole('ADMIN')")
                                 /*.antMatchers("/agent/**}").hasAuthority("Role_ADMIN")
                                 .antMatchers("/report/**}").hasAuthority("Role_ADMIN")
                                 .antMatchers("/companyclient/**}").hasAuthority("Role_ADMIN")
                                 .antMatchers("/particularclient/**}").hasAnyAuthority("Role_ADMIN","Role_PARTICULARCLIENT")
                                 .antMatchers("/companyclient/**}").hasAnyAuthority("Role_ADMIN","Role_COMPANYCLIENT")
                                 .antMatchers("/product/**}").hasAnyAuthority("Role_ADMIN","AGENT")
                                 .antMatchers("/contract/**}").hasAnyAuthority("Role_ADMIN","AGENT")
                                 .antMatchers("/sinister/**}").hasAnyAuthority("Role_PARTICULARCLIENT","Role_COMPANYCLIENT","Expert")*/
                                 .anyRequest().authenticated()
                                 .and().httpBasic().
                                 and().
                                 csrf().disable();
	}

}
