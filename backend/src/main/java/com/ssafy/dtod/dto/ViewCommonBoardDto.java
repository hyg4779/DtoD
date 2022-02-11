package com.ssafy.dtod.dto;

import java.util.List;

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
public class ViewCommonBoardDto {
	
	private Long cboardId;
	private User user;
	private String cboardType;
	private String cboardTitle;
	private String cboardCode;
	private String cboardContent;
	private List<String> cboardTechstack;
	
}
