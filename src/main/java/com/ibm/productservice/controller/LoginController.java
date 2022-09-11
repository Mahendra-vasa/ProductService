package com.ibm.productservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ibm.productservice.model.JwtTokenResponse;
import com.ibm.productservice.model.UserData;
import com.ibm.productservice.util.JwtTokenUtil;

@RestController
public class LoginController {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public JwtTokenResponse login(@RequestBody UserData userData) {
		//assumimg the authentication is success
		String jwtToken = jwtTokenUtil.generateToken(userData.getUsername());
		JwtTokenResponse response = new JwtTokenResponse();
		response.setJwttoken(jwtToken);
		log.info("jwtToken: "+jwtToken);
		return response;
	}

}
