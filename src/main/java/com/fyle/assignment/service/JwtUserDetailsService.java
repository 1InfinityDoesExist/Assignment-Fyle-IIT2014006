package com.fyle.assignment.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fyle.assignment.dao.UserDao;
import com.fyle.assignment.model.DAOUser;
import com.fyle.assignment.model.UserDTO;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	DAOUser user = userDao.findByUsername(username);
	if (user == null) {
	    throw new UsernameNotFoundException("User not found with username: " + username);
	}
	return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
		new ArrayList<>());
    }

    public DAOUser save(UserDTO user) {
	DAOUser newUser = new DAOUser();
	newUser.setUsername(user.getUsername());
	newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
	newUser.setEmail(user.getEmail());
	newUser.setMobilePhone(user.getMobilePhone());
	return userDao.save(newUser);
    }
}