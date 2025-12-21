package br.com.kaiojoaorobert.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfiguration {

	@Value("${jwt.secret}")
	private String secret;
	
	@Autowired AuthenticationFilter authenticationFilter;

	@Bean
	FilterRegistrationBean<AuthenticationFilter> registrationFilter() {
		
		var filter = new FilterRegistrationBean<AuthenticationFilter>();
		filter.setFilter(authenticationFilter);
		
		filter.addUrlPatterns("/api/*");
		
		return filter;
	}
}