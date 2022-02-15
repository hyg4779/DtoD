package com.ssafy.dtod.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.ssafy.dtod.model.User;

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
public class RegistStudyboardDto {
	
	@NotNull
	private String sboardTitle;
	@NotNull
	private String sboardContent1;
	@NotNull
	private String sboardContent2;
	@NotNull
	private String sboardContent3;
	
	private List<String> sboardTechstack;
	private User user;
	private String sboardImg;
	private String sboardIngdate;
	private String sboardJoindate;
	private Integer sboardPerson;
	
}
