package com.nt.service;

import java.util.List;

import com.nt.model.ActiveUser;
import com.nt.model.LoginCradentials;
import com.nt.model.RecoverPassword;
import com.nt.model.UserAccount;

public interface IUserMngService {
	
	public String registerUser(UserAccount user) throws Exception ;
	public String activateUser(ActiveUser active);
	public String login(LoginCradentials cred);
	public List<UserAccount> getAllUsers();
	public UserAccount getUserById(Integer id);
	public UserAccount showUserByEmailAndName(String email,String name);
	public String deleteUserById(Integer id);
	public String updateUser(UserAccount user);
	public String updateActiveStatus(Integer id,String status);
	public String recoverPassword(RecoverPassword recover) throws Exception;
}
