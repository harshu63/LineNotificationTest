package com.notification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Configuration
public class LineLoginConfig {

	@EnableWebSecurity
	public class LineLoginSecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().anyRequest().authenticated().and().oauth2Login();
		}
	}

	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		return new InMemoryClientRegistrationRepository(this.lineClientRegistration());
	}

	private ClientRegistration lineClientRegistration() {
		return ClientRegistration.withRegistrationId("line").clientId("1655969000")
                		.clientSecret("1a77c489c1f7fd9e5616b4157c3b2a0c")
				.clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.redirectUriTemplate("https://linenotify541.herokuapp.com/").scope("profile")
				.authorizationUri("https://access.line.me/oauth2/v2.1/authorize")
				.tokenUri("https://api.line.me/oauth2/v2.1/token").userNameAttributeName("userId")
				.userInfoUri("https://api.line.me/v2/profile").clientName("LINE").build();
	}

}
