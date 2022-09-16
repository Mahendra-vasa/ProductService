package com.ibm.productservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ibm.productservice.model.JwtTokenResponse;
import com.ibm.productservice.model.UserData;
import com.ibm.productservice.util.JwtTokenUtil;

@RestController
public class LoginController {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	RestTemplate  restTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public JwtTokenResponse login(@RequestBody UserData userData) {
		//assumimg the authentication is success
		ResponseEntity<UserData> userDetailsResponse = restTemplate.getForEntity("http://localhost:8085/getUser/Mahendra", UserData.class);
		UserData userDetails = userDetailsResponse.getBody();
		System.out.println("userDetailsFromUSerService"+userDetailsResponse.getBody().toString());
		String jwtToken = jwtTokenUtil.generateToken(userDetails);
		JwtTokenResponse response = new JwtTokenResponse();
		response.setJwttoken(jwtToken);
		log.info("jwtToken: "+jwtToken);
		return response;
	}

}
