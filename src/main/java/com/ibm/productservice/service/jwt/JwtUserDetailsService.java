package com.ibm.productservice.service.jwt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.productservice.model.UserData;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	RestTemplate  restTemplate;
	
	@Override
	public UserDetails loadUserByUsername(String userName) 
			throws UsernameNotFoundException {
		//there should be some database logic
		ResponseEntity<UserData> userDetailsResponse = restTemplate.getForEntity("http://localhost:8085/getUser/Mahendra", UserData.class);
		UserData userDetails = userDetailsResponse.getBody();
		System.out.println("loadUserByUsername"+userDetails.getUserName());
		if (userDetails.getUserName().equals(userName)) {
			String password = userDetails.getPassword();
			return new User(userName, password, getAuthority(userDetails.getUserType()));
		} else {
			throw new UsernameNotFoundException("user not found with " + userName);
		}
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(String roleType) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(roleType));
        return authorities;
    }

}

