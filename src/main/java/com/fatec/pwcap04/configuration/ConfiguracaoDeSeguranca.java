package com.fatec.pwcap04.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca  extends WebSecurityConfigurerAdapter {
	
	@Override
	 protected void configure(HttpSecurity http) throws Exception {
	 http.authorizeRequests()
	 	.antMatchers("/admin/cadastro").hasAnyRole("ADMIN", "BIB") //
	 	//.antMatchers("/livro/cadastrar").hasRole("BIB") //somente login maria
	 	.anyRequest().authenticated().and()
	 	.formLogin().loginPage("/login").permitAll().and()
	 	//.logout().logoutSuccessUrl("/login?logout").permitAll();
	 // sample logout customization
	 	.logout().deleteCookies("remove")
	 	.invalidateHttpSession(false)
	 	.logoutUrl("/custom-logout")
	 	.logoutSuccessUrl("/logout-success");
	 }
	
	 @Override
	 public void configure(AuthenticationManagerBuilder auth) throws Exception {
	 auth.inMemoryAuthentication()
	 	.withUser("admin").password(pc().encode("adm123")).roles("ADMIN").and();
	 	//.withUser("maria").password(pc().encode("456")).roles("BIB");
	 }
	 
	 @Bean
	 public BCryptPasswordEncoder pc() {
		 return new BCryptPasswordEncoder();
	 }
	 
	 @Override
	 public void configure(WebSecurity web) throws Exception {
		 web.ignoring().antMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/h2-console/**");
	 } 
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	 final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	 source.registerCorsConfiguration ("/**", new CorsConfiguration().applyPermitDefaultValues());
	 return source;
	}
	

}