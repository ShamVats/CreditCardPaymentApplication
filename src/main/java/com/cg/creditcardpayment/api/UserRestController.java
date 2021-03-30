package com.cg.creditcardpayment.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.creditcardpayment.exception.UserException;
import com.cg.creditcardpayment.model.ChangePassword;
import com.cg.creditcardpayment.model.SignUp;
import com.cg.creditcardpayment.model.UserModel;
import com.cg.creditcardpayment.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	private IUserService userService;
	
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserModel>> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}
	
	@GetMapping("/getUser/{userId}")
	public ResponseEntity<UserModel> findById(@PathVariable("userId")String userId) throws UserException {
		return new ResponseEntity<>(userService.findById(userId),HttpStatus.FOUND);
	}
		
	@PostMapping("/signIn")
	public ResponseEntity<String> signIn(@RequestBody UserModel user) throws UserException{
		ResponseEntity<String> response=null;
		if(userService.existsById(user.getUserId())) {
			if(userService.signIn(user)) {
				response=new ResponseEntity<>("Signed In "+user.getUserId(),HttpStatus.ACCEPTED);
			}else {
				response=new ResponseEntity<>("Login Id and password did not match",HttpStatus.UNAUTHORIZED);
			}		
		}else {
			response=new ResponseEntity<>("User with "+user.getUserId()+" Does not exists",HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	
	@PostMapping("/addUser")
	public ResponseEntity<String> add(@RequestBody UserModel user) throws UserException {
		userService.add(user);
		return new ResponseEntity<>("User is Added",HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) throws UserException {
		userService.deleteById(userId);
		return new ResponseEntity<>("User is Deleted",HttpStatus.OK);
	}
	
	@PutMapping("/changePassword")
	public ResponseEntity<String> updateUser(@RequestBody ChangePassword changePassword ) throws UserException {
		ResponseEntity<String> response=null;
		UserModel user=userService.findById(changePassword.getUserId());
		if(user!=null) {
			if(userService.changePassword(changePassword)) {
				response=new ResponseEntity<>("Password Changed Succesfull!",HttpStatus.ACCEPTED);
			}else {
				response=new ResponseEntity<>("Password not Changed",HttpStatus.NOT_ACCEPTABLE);
			}
		}else {
			response=new ResponseEntity<>(HttpStatus.NO_CONTENT);	
		}
		return response;
	}
	
	@PutMapping("/signUp")
	public ResponseEntity<UserModel> signUp(@RequestBody SignUp signUp ) throws UserException {
		ResponseEntity<UserModel> response=null;
		UserModel user=userService.findById(signUp.getUserId());
		if(user!=null) {
			user=userService.signUp(signUp);
			response=new ResponseEntity<>(user,HttpStatus.ACCEPTED);
		}else {
			response=new ResponseEntity<>(HttpStatus.NO_CONTENT);	
		}
		return response;
	}
	
}
