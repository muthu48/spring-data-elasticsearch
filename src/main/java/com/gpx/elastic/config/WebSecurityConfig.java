package com.gpx.elastic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UrlPathHelper;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {

	  http.cors().and().csrf().disable()
	  .authorizeRequests()
	  .antMatchers(HttpMethod.GET, "/search/user").permitAll();
	
	}

/*@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
// Create a default account
    auth.inMemoryAuthentication()
    .withUser("admin")
    .password("password")
    .roles("ADMIN");
    
  auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);

}*/

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }	

  @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**")
	                .allowedOrigins(new String[]{"http://localhost:4200"})
	                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS");
	            }
	            
	            public void configurePathMatch(PathMatchConfigurer configurer) {
	                UrlPathHelper urlPathHelper = new UrlPathHelper();
	                urlPathHelper.setRemoveSemicolonContent(false);
	                configurer.setUrlPathHelper(urlPathHelper);
	            }

	        };
	    }
	   
}
