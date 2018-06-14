package org.sid.securite;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableWebSecurity//pour activer la securite web
@EnableGlobalMethodSecurity(securedEnabled=true)//active l'annotation @secured
public class SecuriteConfig extends WebSecurityConfigurerAdapter{
    
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 //les utilisateur sont stockés en memoire
		auth.inMemoryAuthentication().withUser("admin").password("12").roles("ADMIN","USER");
		auth.inMemoryAuthentication().withUser("user").password("12").roles("USER");
		 */
		//requets sql que Spring Securite doit executer afin de recuperer les utilisateurs et les roles
			auth.jdbcAuthentication()
			.dataSource(dataSource) 
			// la requete que spring doit executer pour recuperer les utilisateurs
			.usersByUsernameQuery("Select username as principal,password as credentials,active from users where username = ?")
			//la requete que spring doit executer pour recuperer les roles
			.authoritiesByUsernameQuery("Select username as  principal,roles as role from users_roles where username = ?")
			//après avoir recuperer les roles spring leur rajoute ce prefixe 
			.rolePrefix("ROLE_")
			//Spring utilise la fonction Md5 pour decoder le password afin de le comparer
			.passwordEncoder(new Md5PasswordEncoder());

	}
	
	@Override
	//pour definir les regles d'authencite
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
		//page d'authentification
		.loginPage("/login");
		 http.authorizeRequests()
	     //les url /operation et /consulterCompte sont accessible par USER
	    .antMatchers("/operations","/consulterCompte").hasRole("USER");

		
		http.authorizeRequests()
		//les url /saveOperation sont accessible par ADMIN
		.antMatchers("/saveOperation").hasRole("ADMIN");
		//c'est il n'a pas le droit d'acces il vau etre rederigé vers page 403
		http.exceptionHandling().accessDeniedPage("/403");
	}

}
