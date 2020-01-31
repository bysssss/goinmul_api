package com.bysssss.goinmul.api.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertyC {
	@Value("${spring.profiles}")
	public String springProfiles;

	@Value("${crypto.password-flag}")
	public void setCryptoPasswordFlag(Boolean cryptoPasswordFlag) {
		PropertyC.cryptoPasswordFlag = cryptoPasswordFlag;
	}
	public static Boolean cryptoPasswordFlag;
	
	@Value("${crypto.privacy-flag}")
	public void setCryptoPrivacyFlag(Boolean cryptoPrivacyFlag) {
		PropertyC.cryptoPrivacyFlag = cryptoPrivacyFlag;
	}
	public static Boolean cryptoPrivacyFlag;
	
	@Value("${crypto.key}")
	public void setCryptoKey(String cryptoKey) {
		PropertyC.cryptoKey = cryptoKey;
	}
	public static String cryptoKey;
}
