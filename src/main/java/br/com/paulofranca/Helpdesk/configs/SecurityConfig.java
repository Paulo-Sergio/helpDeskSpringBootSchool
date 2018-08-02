package br.com.paulofranca.Helpdesk.configs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired 
	private DataSource dataSource;
	
	@Autowired 
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * Vai bloquear as paginas
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/login")
				.permitAll()
			.antMatchers("/registration")
				.permitAll()
			.antMatchers("/**")
				.hasAnyAuthority("ADMIN", "USER")
				.anyRequest()
			.authenticated()
				.and()
				.csrf()
				.disable()
				.formLogin()
			.loginPage("/login")
				.failureUrl("/login?errors=true")
				.defaultSuccessUrl("/")
				.usernameParameter("email")
				.passwordParameter("password")
			.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
					.and()
					.exceptionHandling()
					.accessDeniedPage("/denied");
	}
	
	/**
	 * Desbloquear alguns itens estaticos
	 */
	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity
			.ignoring().antMatchers("/static/**", "/js/**", "/css/**", "videos/**", "/images/**", "/resources/**");
	}
	
	/**
	 * Utilizando autenticação para desbloquear tudo
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
				.usersByUsernameQuery("SELECT usr.email, usr.password, usr.active FROM users usr WHERE usr.email = ? AND usr.active = 1")
				.authoritiesByUsernameQuery("SELECT usr.email, rl.name FROM users usr "
						+ "INNER JOIN users_roles usrr ON (usr.id = usrr.user_id) "
						+ "INNER JOIN roles rl ON (usrr.role_id = rl.id) "
						+ "WHERE usr.email = ? AND usr.active = 1")
				.dataSource(this.dataSource)
				.passwordEncoder(this.bCryptPasswordEncoder);
	}
	
}
