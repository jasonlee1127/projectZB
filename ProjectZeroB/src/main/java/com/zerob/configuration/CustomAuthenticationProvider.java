package com.zerob.configuration;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO: Spring Security 인증 처리 리팩토리 포인트
 * 2019-04-02 - jasonLee
 * Spring security 인증 처리 custom화
 * 일단 무조건 인증 요청시 통과 처리
 * @author jason
 */
@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		/**
		 * 2019-04-02 - jasonLee
		 * 인증시 넘어온 코드 (login기준 ID)
		 */
		String name = authentication.getName();
		log.debug("CustomAuthenticationProvider >> name >> " + name);
		
		/**
		 * 2019-04-02 - jasonLee
		 * 인증시 넘어온 코드 (login기준 암호)
		 * 우리는 현재 인증에 대한 암호는 사용하지 않기로 하여 일단 값은 빈값으로 처리
		 */
//	    String password = authentication.getCredentials().toString();
		String password = "";
		log.debug("CustomAuthenticationProvider >> password >> " + authentication.getCredentials().toString());
	    
        return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
