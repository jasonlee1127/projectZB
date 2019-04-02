package com.zerob.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 2019.03.29 - jasonLee
 * RestController 테스트를 위해 Security의 접속 인증 제한을 허용하기 위한 class 생성
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 2019-04-02 - jasonLee
	 * resource(정적)별 참조 및 호출에 대한 접근 허용 여부 설정
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resource/**");
	}
	
	/**
	 * 2019-04-02 - jasonLee
	 * api의 인증 권한별 접근 허용 여부 설정
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		/**
		 * permitAll: 전체 허용
		 * authenticated: 로그인 인증 사용자 접근 허용
		 */
		.antMatchers("/**").permitAll().and().httpBasic();
		
		/**
		 * Spring Security 4.x 이상 버전에서 추가된 Security 보안
		 * default: enable
		 * - 해당 기능은 중지 시킴
		 */
		http.csrf().disable();
	}
	
}
