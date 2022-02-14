package com.ssafy.dtod.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dtod.dto.RegistStudyRoomDto;
import com.ssafy.dtod.dto.ViewStudyRoomDto;
import com.ssafy.dtod.model.StudyRoom;
import com.ssafy.dtod.repository.StudyRoomRepository;

@Service
public class StudyRoomService {

	@Autowired
	private StudyRoomRepository studyroomrepository;
	
	@Transactional
	public List<StudyRoom> listRoom(){
		return studyroomrepository.findAll();
	}
	
	@Transactional
	public Optional<StudyRoom> viewRoom(Long roomId){
		return studyroomrepository.findById(roomId);
	}
	
	@Transactional
	public StudyRoom saveRoom(RegistStudyRoomDto dto) {
		StudyRoom studyroom = StudyRoom.builder()
				.user(dto.getUser())
				.roomTitle(dto.getRoomTitle())
				.roomPwd(dto.getRoomPwd())
				.roomPerson(dto.getRoomPerson())
				.roomTechstack(String.join(",", dto.getRoomTechstack()))
				.roomIngdate(dto.getRoomIngdate())
				.roomIngday(String.join(",", dto.getRoomIngday()))
				.roomContent1(dto.getRoomContent1())
				.roomContent2(dto.getRoomContent2())
				.build();
		return studyroomrepository.save(studyroom);
	}
	
	@Transactional
	public StudyRoom updateRoom(ViewStudyRoomDto dto) {
		StudyRoom updateroom = studyroomrepository.findById(dto.getRoomId()).orElseThrow(() -> new IllegalArgumentException("해당 방이 없습니다. id = " + dto.getRoomId()));
		updateroom.setRoomTitle(dto.getRoomTitle());
		updateroom.setRoomPwd(dto.getRoomPwd());
		updateroom.setRoomPerson(dto.getRoomPerson());
		updateroom.setRoomTechstack(String.join(",", dto.getRoomTechstack()));
		updateroom.setRoomIngdate(dto.getRoomIngdate());
		updateroom.setRoomIngday(String.join(",", dto.getRoomIngday()));
		updateroom.setRoomContent1(dto.getRoomContent1());
		updateroom.setRoomContent2(dto.getRoomContent2());
		
		return studyroomrepository.save(updateroom);
	}
	
	@Transactional
	public void deleteRoom(Long roomId) {
		StudyRoom deleteroom = studyroomrepository.findById(roomId).orElseThrow(()-> new IllegalArgumentException("해당 방이 없습니다" + roomId));
		studyroomrepository.delete(deleteroom);
	}
	
	@Transactional
	public StudyRoom findByRoomId(Long roomId) {
		return studyroomrepository.findById(roomId).get();
	}
	
	
}
