package com.ssafy.dtod.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dtod.dto.RegistSboardCommentDto;
import com.ssafy.dtod.dto.UpdateSboardCommentDto;
import com.ssafy.dtod.model.SboardComment;
import com.ssafy.dtod.model.Studyboard;
import com.ssafy.dtod.repository.SboardCommentRepository;

@Service
public class SboardCommentService {
	
	@Autowired
	private SboardCommentRepository sboardcommentRepository;
	
	@Transactional
	public List<SboardComment> listComment(Studyboard studyboard){
		List<SboardComment> list = sboardcommentRepository.findByStudyboard(studyboard);
		return list;
	}
	
	@Transactional
	public SboardComment registComment(RegistSboardCommentDto registsboardcommentDto) {
		SboardComment sboardcomment = SboardComment.builder()
				.scommentContent(registsboardcommentDto.getScommentContent())
				.scommentTime(LocalDateTime.now())
				.user(registsboardcommentDto.getUser())
				.studyboard(registsboardcommentDto.getStudyboard())
				.build();
		
		return sboardcommentRepository.save(sboardcomment);
	}
	
	@Transactional
	public SboardComment updateComment(UpdateSboardCommentDto updateboardcommentDto) {
		SboardComment updatecomment = sboardcommentRepository.findById(updateboardcommentDto.getScommentId()).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id = " + updateboardcommentDto.getScommentId()));
		updatecomment.setScommentContent(updateboardcommentDto.getScommentContent());
		updatecomment.setScommentTime(LocalDateTime.now());
		return sboardcommentRepository.save(updatecomment);
	}
	
	@Transactional
	public void deleteComment(Long scommentId) {
		SboardComment deletecomment = sboardcommentRepository.findById(scommentId).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다" + scommentId));
		sboardcommentRepository.delete(deletecomment);
	}
}
