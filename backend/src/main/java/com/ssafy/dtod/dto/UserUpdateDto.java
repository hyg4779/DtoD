package com.ssafy.dtod.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	
	@NotNull
	private String userEmail;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull
	private String userPwd;

	@NotNull
	private String userName;

	private List<String> userTechstack;

	@NotNull
	private String userJobs;
	
	private String userImg;
}