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
	           .withUser("Piotr_W.")
	           .password(passwordEncoder().encode("password"))
	           .roles("ADMIN")
	           .and()
	           .withUser("Krzysztof_K.")
	           .password(passwordEncoder().encode("password"))
	           .roles("ADMIN")
	           .and()
	           .withUser("Ewa_W.-M.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Sebastian_K.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Magdalena_S.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Marika_J.-B.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Emilia_B.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Wojciech_P.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Krzysztof_S.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Henryk_S.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Sebastian_R.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Piotr_S.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Arkadiusz_O.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Kamil_S.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Mateusz_C.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Jaroslaw_N.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Sebastian_S.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Maciej_D.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Karol_D.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Wojciech_G.")
	           .password(passwordEncoder().encode("password"))
	           .roles("USER")
	           .and()
	           .withUser("Ryszard_G.")
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
