package com.zerob.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO: Security Denined 리팩토리 포인트
 * 2019-04-02 - jasonLee
 * Spring security 인증 실패시 처리 부분이며
 * 해당 부분은 돌아가는 버전으로 일단 정리함 해당 부분은 denied를 어떻게 처리할지
 * 리팩토리 포인트임
 * @author jason
 */
@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	
	@Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException ex) throws IOException, ServletException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            log.info(auth.getName()
                    + " was trying to access protected resource: "
                    + request.getRequestURI());
        }

        response.sendRedirect(request.getContextPath() + "/access-denied");

    }

}
