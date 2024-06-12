package com.pritam.fabrication.ecommerce.service;



import java.util.List;

import com.pritam.fabrication.ecommerce.exception.UserException;
import com.pritam.fabrication.ecommerce.model.User;


public interface UserService {
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
	
	public List<User>findAllUsers();

	public void deleteUserById(Long userId) throws UserException;
	
}
