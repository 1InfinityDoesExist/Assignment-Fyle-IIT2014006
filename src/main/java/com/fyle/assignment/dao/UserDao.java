package com.fyle.assignment.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fyle.assignment.model.DAOUser;

@Repository
public interface UserDao extends CrudRepository<DAOUser, Long> {
	//@Query(nativeQuery = true, value = "select dao_user from dao_user where username=?1")
	public DAOUser findByUsername(String username);
	
}