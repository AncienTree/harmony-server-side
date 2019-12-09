package pl.entpoint.harmony.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

/**
 * @author Mateusz Dąbek
 * Created on Nov 11, 2019
 * m.dabek@entpoint.pl
 */

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "pl.entpoint.harmony")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private DataSource securityDataSource;
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.jdbcAuthentication().dataSource(securityDataSource)
//			.usersByUsernameQuery(
//				"select login,password,status from user where login=?")
//			.authoritiesByUsernameQuery(
//				"select role from user where login=?");
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.antMatchers("/api/users/**").hasAnyRole("HR", "ADMIN")
//			.antMatchers("/resources/**").permitAll()
////			.and()
////			.httpBasic()
//			.and()
//			.formLogin()
//				.permitAll()
//			.and()
//			.logout().permitAll()
//			.and()
//			.exceptionHandling().accessDeniedPage("/access-denied")
//			//TODO usunąć finalnie
//			.and()
//			.csrf().disable();
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		@SuppressWarnings("deprecation")
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
			.withUser(users.username("test").password("abc123").roles("EMPLOYEE"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/api/users/**").permitAll()
			.and()
			.httpBasic()
			//TODO usunąć finalnie
			.and()
			.csrf().disable();
	}
}
