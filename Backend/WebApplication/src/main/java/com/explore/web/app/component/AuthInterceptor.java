package com.explore.web.app.component;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.explore.web.app.service.UsersService;
import com.explore.web.app.util.AuthUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * This will work as an intercepter for our authentication process
 * @author arpit
 *
 */

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

	@Autowired
	AuthUtil auth;

	@Autowired
	UsersService service;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {
		log.info("prehandle done");
		log.info("........" + request.getRequestURI());
		boolean flag = true;
		try {
			if (request.getRequestURI().equals("/user/login") || request.getRequestURI().equals("/user/register")||request.getRequestURI().equals("/user")) {
				return true;
			}
			String str = request.getHeader("Authorization");
			String token = str.substring(7);
			log.info("Token......." + token);

			log.info("Response............." + auth.validateJwtToken(token));

			  flag=auth.validateJwtToken(token);

			if (flag == false) {
				response.sendError(401);
				return false;
			}
			return true;

		} catch (Exception e) {
			response.sendError(401);
			e.printStackTrace();
			log.info("Unauthorised Access");
			return false;
		}
	}
}