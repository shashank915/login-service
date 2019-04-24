package com.frostinteractive.loginservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
//    @Value("${security.jwt.login-uri}")
    private String Uri = "/login";

//    @Value("${security.jwt.header}")
    private String header = "Authorization";

//    @Value("${security.jwt.prefix}")
    private String prefix = "Bearer";

    //Expiry time for JWT token in seconds
//    @Value("${security.jwt.expiration}")
    private int expiration = 86400;

//    @Value("${security.jwt.secret}")
    private String secret = "FrostLoginSecret";

	public String getUri() {
		return Uri;
	}

	public String getHeader() {
		return header;
	}

	public String getPrefix() {
		return prefix;
	}

	public int getExpiration() {
		return expiration;
	}

	public String getSecret() {
		return secret;
	}
}
