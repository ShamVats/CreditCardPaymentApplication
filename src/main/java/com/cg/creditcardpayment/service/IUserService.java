package com.cg.creditcardpayment.service;

import java.util.List;

import com.cg.creditcardpayment.exception.UserException;
import com.cg.creditcardpayment.model.ChangePassword;
import com.cg.creditcardpayment.model.SignUp;
import com.cg.creditcardpayment.model.UserModel;

public interface IUserService {

	boolean existsById(String userId);
	
	
	UserModel add(UserModel user) throws UserException;	
	UserModel save(UserModel user) throws UserException;
	
	boolean signIn(UserModel user) throws UserException;	
	boolean signOut(UserModel user);
	
	void deleteById(String userId) throws UserException;
	
	UserModel findById(String userId) throws UserException;
	List<UserModel> findAll();
	
	boolean changePassword(ChangePassword changePassword) throws UserException;
	
	UserModel signUp(SignUp signUp) throws UserException;
	
}
