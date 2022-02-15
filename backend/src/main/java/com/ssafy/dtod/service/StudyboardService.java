package com.ssafy.dtod.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dtod.dto.RegistStudyboardDto;
import com.ssafy.dtod.dto.ViewStudyboardDto;
import com.ssafy.dtod.model.Studyboard;
import com.ssafy.dtod.model.User;
import com.ssafy.dtod.repository.StudyboardRepository;

@Service
public class StudyboardService {
	
	@Autowired
	private StudyboardRepository studyboardRepository;
	
	// 리스트 출력
	@Transactional
	public List<Studyboard> findAll() {
		return studyboardRepository.findAll();
	}
	
	// 게시글 보기
	@Transactional
	public Optional<Studyboard> ViewBoard(Long sboardId) {
		return studyboardRepository.findById(sboardId);
	}
	
	// 게시글 등록
	@Transactional
	public Studyboard saveBoard(RegistStudyboardDto resistStudyDto) {
		Studyboard studyboard = Studyboard.builder()
				.sboardTitle(resistStudyDto.getSboardTitle())
				.sboardContent1(resistStudyDto.getSboardContent1())
				.sboardContent2(resistStudyDto.getSboardContent2())
				.sboardContent3(resistStudyDto.getSboardContent3())
				.sboardTechstack(String.join(",", resistStudyDto.getSboardTechstack()))
				.user(resistStudyDto.getUser())
				.sboardImg(resistStudyDto.getSboardImg())
				.sboardIngdate(resistStudyDto.getSboardIngdate())
				.sboardJoindate(resistStudyDto.getSboardJoindate())
				.sboardPerson(resistStudyDto.getSboardPerson())
				.build();
		return studyboardRepository.save(studyboard);
	}
	
	// 게시글 수정
	@Transactional
	public Studyboard updateBoard(ViewStudyboardDto viewstudyboardDto) throws IOException{
		Studyboard updateboard = studyboardRepository.findById(viewstudyboardDto.getSboardId()).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + viewstudyboardDto.getSboardId()));
		System.out.println(updateboard.getSboardId());
		
//		updateboard = Studyboard.builder()
//				.sboardTitle(viewstudyboardDto.getSboardTitle())
//				.sboardContent1(viewstudyboardDto.getSboardContent1())
//				.sboardContent2(viewstudyboardDto.getSboardContent2())
//				.sboardContent3(viewstudyboardDto.getSboardContent3())
//				.sboardTechstack(String.join(",", viewstudyboardDto.getSboardTechstack()))
//				.user(viewstudyboardDto.getUser())
//				.build();
		updateboard.setSboardTitle(viewstudyboardDto.getSboardTitle());
		updateboard.setSboardContent1(viewstudyboardDto.getSboardContent1());
		updateboard.setSboardContent2(viewstudyboardDto.getSboardContent2());
		updateboard.setSboardContent3(viewstudyboardDto.getSboardContent3());
		updateboard.setSboardTechstack(String.join(",", viewstudyboardDto.getSboardTechstack()));
		updateboard.setSboardIngdate(viewstudyboardDto.getSboardIngdate());
		updateboard.setSboardJoindate(viewstudyboardDto.getSboardJoindate());
		updateboard.setSboardPerson(viewstudyboardDto.getSboardPerson());
		return studyboardRepository.save(updateboard);
	}
	
//	public void updateBoard(ViewStudyboardDto viewstudyboardDto) {
//		Studyboard updateboard = studyboardRepository.findById(viewstudyboardDto.getSboardId());
//		updateboard = Studyboard.builder()
//				.sboardTitle(viewstudyboardDto.getSboardTitle())
//				.build();
//	}
	// 게시글 삭제
	@Transactional
	public void delete(Long sboardId) {
		Studyboard deleteboard = studyboardRepository.findById(sboardId).orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다" + sboardId));
		studyboardRepository.delete(deleteboard);
	}
		
	// 댓글용 id로 정보 가져오기
	@Transactional
	public Studyboard findByBoardId(Long sboardId) {
		return studyboardRepository.findById(sboardId).get();
	}
	
	public List<Studyboard> findByTechstack(User user) {
		List<String> list = new ArrayList<String>();
//		System.out.println(user.getUserTechstack());
		String[] splitTech = user.getUserTechstack().split(",");
		for(int i=0; i<splitTech.length; i++) {
//		System.out.println(splitTech[i]);
		}
		for(int i=0; i<splitTech.length; i++) {
			list.add(splitTech[i]);
		}
//		System.out.println(list);
		List<Studyboard> listBoard = studyboardRepository.findAll();
		List<Studyboard> recommendBoard = new ArrayList<Studyboard>();
		for(int i=0; i<listBoard.size(); i++) {
			for(int j=0; j<list.size(); j++) {
				if(listBoard.get(i).getSboardTechstack().contains(list.get(j))) {
					recommendBoard.add(listBoard.get(i));
				}
			}
		}
		for(int i=0; i<recommendBoard.size(); i++) {
			for(int j=i+1; j<recommendBoard.size(); j++) {
				if(recommendBoard.get(i).getSboardId() == recommendBoard.get(j).getSboardId()) {
					recommendBoard.remove(j);
				}
			}
		}
		return recommendBoard;
	}
	
}
