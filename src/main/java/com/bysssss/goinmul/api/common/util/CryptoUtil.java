package com.bysssss.goinmul.api.common.util;

import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.util.StringUtils;

import com.bysssss.goinmul.api.component.PropertyC;

public class CryptoUtil {
	private static String encAes128(String plaintext) {
		String key = PropertyC.cryptoKey;
		if( StringUtils.isEmpty(key) || StringUtils.isEmpty(plaintext)) {
			return null;
		}
		
		String ciphertext = null;
		try {
			// 16Byte == 16*8Bit == 128Bit
			String iv = key.substring(0, 16); 
	        byte[] keyByte = key.getBytes("UTF-8");
	        byte[] keySubByte = new byte[16];
	        System.arraycopy(keyByte, 0, keySubByte, 0, 16);
	        Key keySpec = new SecretKeySpec(keySubByte, "AES");
	        
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));			
	        byte[] encrypted = cipher.doFinal(plaintext.getBytes("UTF-8"));
	        ciphertext = new String(Base64.encodeBase64(encrypted));	
	        //ciphertext = new String(Hex.encodeHex(encrypted));
		} catch (Exception e) {
			return null;
		}
		
		return ciphertext;
    }
	
	private static String decAes128(String ciphertext) {
		String key = PropertyC.cryptoKey;
		if( StringUtils.isEmpty(key) || StringUtils.isEmpty(ciphertext)) {
			return null;
		}
		
		String plaintext = null;
		try {
			// 16Byte == 16*8Bit == 128Bit
			String iv = key.substring(0, 16);
	        byte[] keyByte = key.getBytes("UTF-8");
	        byte[] keySubByte = new byte[16];
	        System.arraycopy(keyByte, 0, keySubByte, 0, 16);
	        Key keySpec = new SecretKeySpec(keySubByte, "AES");
	        
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));
	        byte[] decrypted = Base64.decodeBase64(ciphertext.getBytes("UTF-8"));	
	        //byte[] decrypted = Hex.decodeHex(ciphertext.getBytes("UTF-8"));
	        plaintext =  new String(cipher.doFinal(decrypted));
		} catch (Exception e) {
			return null;
		}
     
		return plaintext;
    }
	
	private static String hashSha256(String plain) {
		String key = PropertyC.cryptoKey;
		if( StringUtils.isEmpty(key) || StringUtils.isEmpty(plain)) {
			return null;
		}
		
		String cipher = null;
        try{
        	plain += key;
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(plain.getBytes("UTF-8"));
            byte hashed[] = md.digest();
            cipher = new String(Hex.encodeHex(hashed));
        }catch(Exception e){
        	return null;
        }
        
        return cipher;
    }
	
	private static String hashMD5(String plain) {
		if( StringUtils.isEmpty(plain)) {
			return null;
		}
		
		String cipher = null;
		try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plain.getBytes("UTF-8"));
            byte hashed[] = md.digest();
            cipher = new String(Hex.encodeHex(hashed));
        }catch(Exception e){
        	cipher = null;
        }
		
		return cipher;
	}
	
	public static String hashPassword(String password) {
		String hash = null;
		if( PropertyC.cryptoPasswordFlag) {
			hash = CryptoUtil.hashSha256(password);
		}
		if( StringUtils.isEmpty(hash)) {
			return password;
		}
		
		return hash;
	}
	
	public static String encPrivacy(String privacy) {
		String enc = null;
		if( PropertyC.cryptoPrivacyFlag) {
			enc = CryptoUtil.encAes128(privacy);
		}
		if( StringUtils.isEmpty(enc)) {
			return privacy;
		}
		
		return enc;
	}
	
	public static String decPrivacy(String privacy) {
		String dec = null;
		if( PropertyC.cryptoPrivacyFlag) {
			dec = CryptoUtil.decAes128(privacy);
		}
		if( StringUtils.isEmpty(dec)) {
			return privacy;
		}
		
		return dec;
	}
	
	public static String hmac(String body, String method, Long timestamp) {
		StringBuffer sb = new StringBuffer();
		String hmac0 = CryptoUtil.hashMD5(body);
		String hmac1 = sb.append(method).append("\n").append(hmac0).append("\n").append(timestamp).toString();
		String hmac2 = CryptoUtil.hashMD5(hmac1);
		String hmac3 = java.util.Base64.getEncoder().encodeToString(hmac2.getBytes());
		
		return hmac3;
	}
}
