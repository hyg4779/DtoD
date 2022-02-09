package com.ssafy.dtod.dto;

import java.util.List;

import com.ssafy.dtod.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListStudyboardDto {

	private Long sboardId;
	private User user;
	private String sboardTitle;
	private String sboardContent1;
	private String sboardContent2;
	private String sboardContent3;
	private List<String> sboardTechstack;
	private String sboardImg;
	private String sboardIngdate;
	private String sboardJoindate;
	private Integer sboardPerson;
	
	
}
