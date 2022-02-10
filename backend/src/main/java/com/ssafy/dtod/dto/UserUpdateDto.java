package com.ssafy.dtod.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
	
	private String userEmail;

//	private String userPwd;

	private String userName;

	private List<String> userTechstack;

	private String userJobs;
	
	private String userImg;
}