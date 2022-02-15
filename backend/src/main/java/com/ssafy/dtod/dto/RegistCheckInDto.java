package com.ssafy.dtod.dto;

import java.time.LocalDate;

import com.ssafy.dtod.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistCheckInDto {

	private User user;
	private LocalDate checkDate;
}
