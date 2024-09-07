package com.nt.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.cfg.Environment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.nt.entity.UserMaster;
import com.nt.model.ActiveUser;
import com.nt.model.LoginCradentials;
import com.nt.model.RecoverPassword;
import com.nt.model.UserAccount;
import com.nt.repo.IUserMngRepo;
import com.nt.util.EmailUtils;

@Service
public class UserMngServieImpl implements IUserMngService{
	
	@Autowired
	private IUserMngRepo repo;
	
	@Autowired
	private EmailUtils email;
	
	@Autowired
	private org.springframework.core.env.Environment env;
	
	@Override
	public String registerUser(UserAccount user) throws Exception {
		//converting user account data to the user master account data
		UserMaster master = new UserMaster();
		//copy the properties of user account data to the user Master data
		BeanUtils.copyProperties(user, master);
		//setting the ransom password to the master object
		String tempPass = randomPassword(6);
		master.setPassword(tempPass);
		//setting the user as the inactive user
		master.setActive_sw("InActive");
		//saving the User Master Object into the DB
	   UserMaster um = 	repo.save(master);
	   
	   //TODO :: send the random password and some text to the user Email adress
	   //perform send mail operation
	   String subject = "User Registration Sucessfull";
	   String body  = readEmailMsg(env.getProperty("email.reg"),user.getName() , tempPass);
	   email.sendEmailMessage(user.getEmail(), subject, body);
	   //return the mag as the object is  saved
		return um.getId()+" with the id value The User Object is Saved";
	}

	@Override
	public String activateUser(ActiveUser active) {
		//find that the user object is avilable or not
		UserMaster master = repo.findByEmailAndPassword(active.getEmail(), active.getTempPass());
		if(master==null) {
			return "User Object is not found for the Activation";
		}else {
			//if user is avilable set password and set user status as active
			master.setPassword(active.getConfirmPass());
			master.setActive_sw("Active");
			//save the user master object
		    repo.save(master);
			return "User Object is Found and Updated the Status as The active User";
		}

	}

	@Override
	public String login(LoginCradentials cred) {
		UserMaster master = new UserMaster();
		BeanUtils.copyProperties(cred, master);
		Example<UserMaster> ex= Example.of(master);
		List<UserMaster> um = repo.findAll(ex);
		if(um.size()==0) {
			return "invalid credentials login not done";
		}else {
			UserMaster usm = um.get(0);
			if(usm.getActive_sw().equalsIgnoreCase("Active"))
			{
				return "valid credentials and login sucessfully";
			}else{ 
				return "User Is Not In Active Status";
				}
		}
	}

	@Override
	public List<UserAccount> getAllUsers() {
		List<UserMaster> list = repo.findAll();
		List<UserAccount> account = new ArrayList<UserAccount>();
		list.forEach(info->{
			UserAccount user = new UserAccount();
			BeanUtils.copyProperties(info, user);
			account.add(user);
		});
		return account;
	}

	@Override
	public UserAccount getUserById(Integer id) {
		UserMaster user = repo.findById(id).orElseThrow(()-> new  IllegalArgumentException("User Object is Not found with id value "+id));
		UserAccount account =  new UserAccount();
		BeanUtils.copyProperties(user, account);
		return account;
	}

	@Override
	public UserAccount showUserByEmailAndName(String email, String name) {
		UserMaster master = repo.findByNameAndEmail(name,email);
		UserAccount account=null;
		if(master!=null) {
			account = new UserAccount();
			BeanUtils.copyProperties(master, account);
			return account;
		}
		return account;
	}

	@Override
	public String deleteUserById(Integer id) {
		Optional<UserMaster> master = repo.findById(id);
		if(master.isPresent()) {
			UserMaster um =master.get();
			repo.deleteById(um.getId());
			return "User Object Is Found And deled With Id Value "+(um.getId());
		}
		return "User Object is Not Found With Id Value"+id;
	}

	@Override
	public String updateUser(UserAccount user) {
		Optional<UserMaster> master=repo.findById(user.getId());
		if(master.isPresent()) {
			UserMaster um = master.get();
			BeanUtils.copyProperties(user, um);
			repo.save(um);
			return " User Object is found and Updated";
		}
		return "User Object is Not Found";
	}

	@Override
	public String updateActiveStatus(Integer id, String status) {
		UserMaster master = repo.findById(id).orElseThrow(()-> new  IllegalArgumentException("User Object is Not found with id value "+id));
		if(master!=null) {
			master.setActive_sw(status);
			repo.save(master);
			return " User Object Is Found And Updated";
		}
		return" User Object Is Not Found";
	}

	@Override
	public String recoverPassword(RecoverPassword recover) throws Exception {
		UserMaster master = repo.findByNameAndEmail(recover.getName(), recover.getEmail());
		if(master!=null) {
			String pass = master.getPassword();
			//TODO send this pass word to The User Email
			String subject = "mail for password recovery";
			String mailBody = readEmailMsg(env.getProperty("email.rec"), recover.getName(), pass);
			email.sendEmailMessage(recover.getEmail(), subject, mailBody);
			return pass;
		}
		return "Invalid UserName And Email";
	}
	
	private String randomPassword(int length) {
		//list of character to choose from to from an string
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; 
		//creating a string buffer size of AlphaNumericString
		StringBuffer buffer = new  StringBuffer(length);
		for(int i=0;i<length;i++) {
			int ch = (int) (chars.length()*Math.random());
			buffer.append(chars.charAt(ch));
		}
		return buffer.toString();
	}
	
	
	//private Mthod to read mail msg body from file
	private String readEmailMsg(String fileName,String fullName,String pwd) throws Exception{
		String mailBody = null;
		String url =" ";
		try {
			FileReader read = new FileReader(fileName);
			BufferedReader br = new BufferedReader(read);
			//read file content to String buffer Object
			StringBuffer buff = new StringBuffer();
			String line =null;
			do {
				line=br.readLine();
				if(line!=null)
				buff.append(line);
			}while(line!=null);
			mailBody = buff.toString();
			mailBody = mailBody.replace("{FULL-NAME}", fullName);
			mailBody = mailBody.replace("{PWD}", pwd);
			mailBody = mailBody.replace("{URL}", url);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return mailBody;
	}
	
	

}
