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
	           .withUser("Piotr W.")
	           .password(passwordEncoder().encode("password"))
	           .roles("ADMIN")
	           .and()
	           .withUser("Krzysztof K.")
	           .password(passwordEncoder().encode("password"))
	           .roles("ADMIN")
	           .and()
	           .withUser("Ewa W.M.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Sebastian K.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Magdalena S.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Marika J.B.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Emila B.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Wojciech P.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Krzysztof S.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Henryk S.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Sebastian R.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Piotr S.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Arkadiusz O.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Kamil S.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Mateusz C.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Jarosław N.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("JSebastian S.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Maciej D.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Karol D.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Wojciech G.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Ryszard G.")
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
