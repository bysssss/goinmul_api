package com.bysssss.goinmul.api.config;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {
	@Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasenames("messages/message");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        resourceBundleMessageSource.setCacheSeconds(360);
        return resourceBundleMessageSource;
    }
	
	private static ResourceBundleMessageSource MessageSource = new ResourceBundleMessageSource();
	static {
		System.out.println("MessageSourceConfig static{}");
		MessageSource.setBasenames("messages/message");
		MessageSource.setDefaultEncoding("UTF-8");
		MessageSource.setCacheSeconds(360);
	}
	
	public static String getMessage(String code) {
		String message = null;
		try {
			message = MessageSource.getMessage(code, null, LocaleContextHolder.getLocale());
		} catch(NoSuchMessageException e) {
			message = "NULL";
		}
		
		return message;
	}
}
