package com.javabrains.springsecurityjwt;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService{
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
	//Here we can get the user details from any where, like DB, Web service call .
	
	
	return new User("foo","foo",new ArrayList<>());
}
}
