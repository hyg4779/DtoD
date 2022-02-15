package com.ssafy.dtod.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dtod.dto.RegistCheckInDto;
import com.ssafy.dtod.model.CheckIn;
import com.ssafy.dtod.model.User;
import com.ssafy.dtod.repository.CheckInRepository;

@Service
public class CheckInService {

	@Autowired CheckInRepository checkinRepository;
	
	@Transactional
	public CheckIn registCheckIn(RegistCheckInDto dto) {
		CheckIn checkin = CheckIn.builder()
				.user(dto.getUser())
				.checkDate(LocalDate.now())
				.build();
		return checkinRepository.save(checkin);
	}
	
	// 원래는 중복된 LocalDate값이 DB에 저장 될 수 없게 해야한다.(프론트단이던, 백단이던) 임시 방편으로 중복값 제거 후 데이터를 뿌려주기 위해 SET을 사용
	@Transactional
	public List<LocalDate> myCheckIn(User user){
		List<CheckIn> allList = checkinRepository.findAll();
		List<LocalDate> myCheck = new ArrayList<LocalDate>();
		for(int i=0; i<allList.size(); i++) {
			if(user.getUserId() == allList.get(i).getUser().getUserId()) {
				myCheck.add(allList.get(i).getCheckDate());
			}
		}
		Set<LocalDate> set = new HashSet<LocalDate>(myCheck);
		myCheck = new ArrayList<LocalDate>(set);
		return myCheck;
	}
}
