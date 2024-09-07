package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "USER_MANG")
@AllArgsConstructor
@NoArgsConstructor
public class UserMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String password;
	private String email;
	private Long mobileNo;
	private Long aadharNo;
	private String gender;
	private LocalDateTime dob;
	private String active_sw;
	
	@CreationTimestamp
//	@Column(insertable = true,updatable = false)
	private LocalDateTime creationTime;
	@UpdateTimestamp
//	@Column(insertable = false,updatable = true)
	private LocalDateTime updationTime;
	
	private String createdBy;
	private String updatedBy;
}
