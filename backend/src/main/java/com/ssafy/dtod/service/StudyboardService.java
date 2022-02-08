package com.ssafy.dtod.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dtod.dto.RegistStudyboardDto;
import com.ssafy.dtod.dto.ViewStudyboardDto;
import com.ssafy.dtod.model.Studyboard;
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
		
	
	
}
