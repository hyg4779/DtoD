package com.ssafy.dtod.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dtod.dto.RegistMyRoomDto;
import com.ssafy.dtod.model.MyRoom;
import com.ssafy.dtod.model.StudyRoom;
import com.ssafy.dtod.model.User;
import com.ssafy.dtod.repository.MyRoomRepository;

@Service
public class MyRoomService {
	
	@Autowired MyRoomRepository myroomRepository;
	
	
	
	@Transactional
	public MyRoom registMyRoom(RegistMyRoomDto dto) {
		MyRoom myroom = MyRoom.builder()
				.user(dto.getUser())
				.studyroom(dto.getStudyroom())
				.build();
		return myroomRepository.save(myroom);
	}
	
	
	@Transactional
	public List<StudyRoom> listMyRoom(User user){
		List<StudyRoom> list = new ArrayList<StudyRoom>();
		list.add(myroomRepository.findByUser(user).getStudyroom());
		return list;
	}
	
	@Transactional
	public void deleteRoom(Long myroomId) {
		MyRoom deleteroom = myroomRepository.findById(myroomId).orElseThrow(()-> new IllegalArgumentException("해당 방이 없습니다" + myroomId));
		myroomRepository.delete(deleteroom);
	}
}
