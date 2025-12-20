package br.com.kaiojoaorobert.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfiguration {

	@Value("${jwt.secretkey}")
	private String jwtSecretkey;

	@Bean
	FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {

		FilterRegistrationBean<AuthenticationFilter> registration = new FilterRegistrationBean<>();

		registration.setFilter(new AuthenticationFilter(jwtSecretkey));

		registration.addUrlPatterns("/api/*");

		return registration;
	}

}