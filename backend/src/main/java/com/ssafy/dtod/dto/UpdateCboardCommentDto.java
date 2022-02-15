package com.ssafy.dtod.dto;

import java.time.LocalDateTime;

import com.ssafy.dtod.model.CommonBoard;
import com.ssafy.dtod.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCboardCommentDto {

	private Long ccommentId;
	private CommonBoard commonboard;
	private User user;
	private String ccommentContent;
	private LocalDateTime ccommentTime;
}
