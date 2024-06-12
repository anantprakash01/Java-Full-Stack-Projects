package com.pritam.fabrication.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pritam.fabrication.ecommerce.exception.UserException;
import com.pritam.fabrication.ecommerce.model.User;
import com.pritam.fabrication.ecommerce.response.ApiResponse;
import com.pritam.fabrication.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	
	
	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization")String jwt)
	throws UserException{
		System.out.println("/api/users/profile");
		User user = userService.findUserProfileByJwt(jwt);
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
	}
	
//	@DeleteMapping("/delete/{id}")
//    public ResponseEntity<ApiResponse> deleteUserByIdHandler(@PathVariable Long userId, @RequestHeader("Authorization")String jwt)throws UserException {
//            User user = userService.findUserProfileByJwt(jwt);
//            userService.deleteUserById(userId);
//            ApiResponse res = new ApiResponse("User deleted successfully",true);
//            return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);
//			
//	}
	
//	@DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteUserByIdHandler(@PathVariable("id") Long id) {
//        try {
//            userService.deleteUserById(id);
//            return ResponseEntity.ok("User deleted successfully");
//        } catch (UserException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteUserByIdHandler(@PathVariable("id") Long id) throws UserException {
		userService.deleteUserById(id);
		ApiResponse res = new ApiResponse("User deleted with id "+id,true);
		
		return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);
	}
}