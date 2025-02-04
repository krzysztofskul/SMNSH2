package pl.krzysztofskul.sensit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SensitSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	   auth.inMemoryAuthentication()
	       .passwordEncoder(new BCryptPasswordEncoder())
	           .withUser("PiotrW")
	           .password(passwordEncoder().encode("password"))
	           .roles("ADMIN")
	           .and()
	           .withUser("KrzysztofK")
	           .password(passwordEncoder().encode("password"))
	           .roles("ADMIN")
	           .and()
	           .withUser("EwaWM")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("SebastianK")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("MagdalenaS")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("MarikaJB")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("EmiliaB")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("WojciechP")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("KrzysztofS")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("HenrykS")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("SebastianR")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("PiotrS")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("ArkadiuszO")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("KamilS")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("MateuszC")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("JaroslawN")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("SebastianS")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("MaciejD")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("KarolD")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("WojciechG")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("RyszardG")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	       ;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "/home", "/css/**", "/js/**", "/pics/**").permitAll()
			.antMatchers("/smnsh").hasRole("{USER, ADMIN}")
			.anyRequest().authenticated()
			.and()
	        	.formLogin()
	            .loginPage("/login").permitAll()
	            .defaultSuccessUrl("/home", true)
	            .failureUrl("/login").permitAll()
            .and()
            	.logout()
	            .permitAll()
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
}
