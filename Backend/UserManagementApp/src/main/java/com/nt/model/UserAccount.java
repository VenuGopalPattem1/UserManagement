package com.nt.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
	private Integer id;
	private String name;
	private String email;
	private Long mobileNo;
	private Long aadharNo;
	private String gender;
	private LocalDateTime dob=LocalDateTime.now();
}
