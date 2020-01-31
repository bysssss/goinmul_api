package com.bysssss.goinmul.api.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.spec.ResultCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SmtpComponent {
	@Autowired
	private ApplicationContext appCtx;
	
	//@Autowired
	//private PropertyComponent propertyComponent;
	
	public String sendEmail(String to, String titla, String content) throws GoinmulException {
		String res = null;	
		try {
			if( StringUtils.isEmpty(to) || StringUtils.isEmpty(titla) || StringUtils.isEmpty(content)) {
				throw new EmailException("to, titla, content err");
			}
			
			Email email = new SimpleEmail();
			email.setHostName("");
			email.setSmtpPort(0);
			email.setCharset("UTF-8");
			email.setFrom("", "", "UTF-8");
			
			email.addTo(to);
			email.setSubject(titla);
			email.setMsg(content);
			
			res = email.send();
			log.info("SmtpComponent sendEmail() : res={}", res);
		} catch (EmailException e) {
			throw new GoinmulException(ResultCode.BAD_GATEWAY, e.getMessage());
		}
		
		return res;
	}
	
	public String sendHtmlEmail(String to, String templete) throws GoinmulException {
		String res = null;	
		try {
			if( StringUtils.isEmpty(to) || StringUtils.isEmpty(templete)) {
				throw new EmailException("to, templete err");
			}
			
			StringBuffer sb = new StringBuffer();
			try {
				BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(appCtx.getResource("").getInputStream(), "UTF-8"));
				for( String line=null; (line=inputStreamReader.readLine()) != null;) {
					sb.append(line);
				}
				inputStreamReader.close();
			} catch (IOException e) {
				throw new EmailException(e.getMessage());
			}
			String message = sb.toString().replace("${templete}", templete);
			
			HtmlEmail htmlEmail = new HtmlEmail();
			//ImageHtmlEmail email = new ImageHtmlEmail();
			htmlEmail.setHostName("");
			htmlEmail.setSmtpPort(0);
			htmlEmail.setCharset("UTF-8");
			//htmlEmail.setContent(htmlCode, "text/html");
			htmlEmail.setFrom("", "", "UTF-8");
			
			htmlEmail.addTo(to);
			//htmlEmail.setDataSourceResolver((DataSourceResolver)new DataSourceUrlResolver(new URL("")));
			htmlEmail.setSubject("");
			htmlEmail.setHtmlMsg(message);
			htmlEmail.setTextMsg("Your email client does not support HTML messages");	// set the alternative message
			
			res = htmlEmail.send();
			log.info("SmtpComponent sendHtmlEmail() : res={}", res);			
		} catch (EmailException e) {
			throw new GoinmulException(ResultCode.BAD_GATEWAY, e.getMessage());
		}
		
		return res;
	}
}
