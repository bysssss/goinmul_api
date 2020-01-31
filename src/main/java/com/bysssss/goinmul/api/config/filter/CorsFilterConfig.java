package com.bysssss.goinmul.api.config.filter;

import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//@Order(Ordered.HIGHEST_PRECEDENCE)
//@Configuration
public class CorsFilterConfig extends CorsFilter {
	public CorsFilterConfig(CorsConfigurationSource configSource) {
		super(configSource);
	}
	
	// TODO : @CrossOrigin
	
	/*
	public CorsFilterConfig() {
        super(CorsFilterConfig.configurationSource());
    }
    
	private static UrlBasedCorsConfigurationSource configurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        List<String> allowedHeaders = Arrays.asList("x-auth-token", "content-type", "X-Requested-With", "XMLHttpRequest");
        List<String> exposedHeaders = Arrays.asList("x-auth-token", "content-type", "X-Requested-With", "XMLHttpRequest");
        List<String> allowedMethods = Arrays.asList("POST", "GET", "DELETE", "PUT", "OPTIONS");
        List<String> allowedOrigins = Arrays.asList("*");
        corsConfiguration.setAllowedHeaders(allowedHeaders);
        corsConfiguration.setExposedHeaders(exposedHeaders);
        corsConfiguration.setAllowedMethods(allowedMethods);
        corsConfiguration.setAllowedOrigins(allowedOrigins);
        corsConfiguration.setMaxAge(36000L);
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
	//*/
}
