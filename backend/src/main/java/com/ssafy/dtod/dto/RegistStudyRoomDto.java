package com.ssafy.dtod.dto;

import java.time.LocalDate;
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
public class RegistStudyRoomDto {

	private User user;
	private String roomTitle;
	private String roomPwd;
	private Integer roomPerson;
	private List<String> roomTechstack;
	private String roomIngdate;
	private List<String> roomIngday;
	private String roomContent1;
	private String roomContent2;
	private String roomImg;
	private LocalDate roomTime;
}
