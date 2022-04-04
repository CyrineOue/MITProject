package tn.MITProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import tn.MITProject.services.MyLogDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyLogDetailsService logDetailsService;
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();

		}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		auth.userDetailsService(logDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() .antMatchers("/authenticate").permitAll()
		                       //  .antMatchers("/admin/**").permitAll()
		                         .antMatchers("/product/**").permitAll()
		                         .antMatchers("/agent/**").permitAll()
		                         .antMatchers("/companyclient/**").permitAll()
		                         .antMatchers("/expert/**").permitAll()
		                         .antMatchers("/sinister/**").permitAll()
		                         .antMatchers("/report/**").permitAll()
		                         .antMatchers("/send-mail/**").permitAll()          
		                         .antMatchers("/admin/**").access("hasRole('ADMIN')")
		                         //.antMatchers("/Contract/retrieve-all-contracts").access("hasRole('ADMIN')")
		                         //.antMatchers("/Contract/add-contract").access("hasRole('ADMIN')")
                                 /*.antMatchers("/agent/**}").hasAuthority("Role_ADMIN")
                                 .antMatchers("/report/**}").hasAuthority("Role_ADMIN")
                                 .antMatchers("/companyclient/**}").hasAuthority("Role_ADMIN")
                                 .antMatchers("/particularclient/**}").hasAnyAuthority("Role_ADMIN","Role_PARTICULARCLIENT")
                                 .antMatchers("/companyclient/**}").hasAnyAuthority("Role_ADMIN","Role_COMPANYCLIENT")
                                 .antMatchers("/product/**}").hasAnyAuthority("Role_ADMIN","AGENT")
                                 .antMatchers("/contract/**}").hasAnyAuthority("Role_ADMIN","AGENT")
                                 .antMatchers("/sinister/**}").hasAnyAuthority("Role_PARTICULARCLIENT","Role_COMPANYCLIENT","Expert")*/
                                 .anyRequest().authenticated()
                                 //.and().formLogin().loginPage("/login").permitAll()
                     			 .and().logout().permitAll()
                                 .and().httpBasic().
                                  and().
                                  exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).
                                  and().
                  				// make sure we use stateless session; session won't be used to
                  				// store user's state.
                  				  sessionManagement()
                  				 .sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                                  and().csrf().disable();
                                 
		// Add a filter to validate the tokens with every request
				http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
