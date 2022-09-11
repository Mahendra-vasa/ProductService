package com.ibm.productservice.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.ibm.productservice.service.jwt.JwtUserDetailsService;
import com.ibm.productservice.util.JwtTokenUtil;

//filter that validates JWT token
//OncePerRequestFilter is part of Spring Security that
//will be executed once for every request
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	private static final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
			FilterChain chain)
			throws ServletException, IOException {
		// this will contain the logic for validating the token value
		String tokenHeader = request.getHeader("Authorization");
		log.info("tokenHeader: "+tokenHeader);
		if(tokenHeader!=null && tokenHeader.startsWith("Bearer")) {
			String jwtToken = tokenHeader.substring(7);
			String userName = jwtTokenUtil.getUserNameFromToken(jwtToken);
			
			//validate the user name
			if(userName!=null) {
				UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userName);
				if(userDetails.getUsername().equals(userName)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					chain.doFilter(request, response);
					
				} else {
					throw new ServletException("token invalid");
				}
			} 
			
		} else {
			chain.doFilter(request, response);
		}
		
	}

}

