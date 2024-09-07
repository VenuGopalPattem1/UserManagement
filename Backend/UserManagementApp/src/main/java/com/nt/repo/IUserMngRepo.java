package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.UserMaster;

public interface IUserMngRepo extends JpaRepository<UserMaster, Integer> {
	public UserMaster findByEmailAndPassword(String email,String password);
	public UserMaster findByNameAndEmail(String name,String email);
}
