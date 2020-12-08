package com.microservice.client.crmclient.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservice.client.crmclient.model.User;
import com.microservice.client.crmclient.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<User> user= userRepository.findByUserName(userName);
		

		user.orElseThrow(()-> new UsernameNotFoundException("not found : "+userName));
		
		return user.map(MyUserDetails ::new).get();

	}
}
