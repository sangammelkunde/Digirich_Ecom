package com.wipro.authenticationservice.service;

import com.wipro.authenticationservice.model.AppUser;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.wipro.authenticationservice.exceptionhandling.AppUserNotFoundException;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;



@Component
@Slf4j
public class LoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	private JwtUtil jwtutil;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private CustomerDetailsService customerDetailservice;

	/**
	 * @param appuser
	 * @return AppUser
	 * @throws AppUserNotFoundException
	 */
	public AppUser userLogin(AppUser appuser) throws AppUserNotFoundException {
		final UserDetails userdetails = customerDetailservice.loadUserByUsername(appuser.getUserid());
		String userid = "";
		String role="";
		String token = "";
		
		LOGGER.info("LOGGING IN WITH USERNAME AND PASSWORD");

		if (userdetails.getPassword().equals(appuser.getPassword()) ) {
			userid = appuser.getUserid();
			token = jwtutil.generateToken(userdetails);
			role = appuser.getRole();
			return new AppUser(userid, null, null, token,role);
		} else {
			throw new AppUserNotFoundException("Username/Password is incorrect...Please check");
		}
	}
}