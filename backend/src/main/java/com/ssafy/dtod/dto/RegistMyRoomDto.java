package com.ssafy.dtod.dto;

import com.ssafy.dtod.model.StudyRoom;
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
public class RegistMyRoomDto {

	private User user;
	private StudyRoom studyroom;
	
}
