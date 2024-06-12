package com.pritam.fabrication.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pritam.fabrication.ecommerce.config.JwtProvider;
import com.pritam.fabrication.ecommerce.exception.UserException;
import com.pritam.fabrication.ecommerce.model.User;
import com.pritam.fabrication.ecommerce.repository.UserRepository;


@Service
public class UserServiceImplementation implements UserService{

	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	
	
	
	public UserServiceImplementation(UserRepository userRepository, JwtProvider jwtProvider) {
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
	}

	@Override
	public User findUserById(Long userId) throws UserException {
		
		Optional<User> user = userRepository.findById(userId);
		
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new UserException("user not found with id: "+ userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		
		String email = jwtProvider.getEmailFromToken(jwt);
		
		User user = userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("User not found with email"+email);
		}
		return user;
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserById(Long userId) throws UserException {
	    Optional<User> user = userRepository.findById(userId);
	    if (user.isPresent()) {
	        userRepository.deleteById(userId);
	    } else {
	        throw new UserException("User not found with id: " + userId);
	    }
	}


}
