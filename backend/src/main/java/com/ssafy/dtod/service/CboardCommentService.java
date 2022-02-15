package com.ssafy.dtod.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dtod.dto.RegistCboardCommentDto;
import com.ssafy.dtod.dto.UpdateCboardCommentDto;
import com.ssafy.dtod.model.CboardComment;
import com.ssafy.dtod.model.CommonBoard;
import com.ssafy.dtod.repository.CboardCommentRepository;

@Service
public class CboardCommentService {
	@Autowired
	private CboardCommentRepository cboardcommentRepository;
	
	@Transactional
	public List<CboardComment> listComment(CommonBoard commonboard){
		List<CboardComment> list = cboardcommentRepository.findByCommonBoard(commonboard);
		return list;
	}
	
	@Transactional
	public CboardComment registComment(RegistCboardCommentDto dto) {
		CboardComment cboardcomment = CboardComment.builder()
				.ccommentContent(dto.getCcommentContent())
				.ccommentTime(LocalDateTime.now())
				.user(dto.getUser())
				.commonboard(dto.getCommonboard())
				.build();
		return cboardcommentRepository.save(cboardcomment);
	}
	
	@Transactional
	public CboardComment updateComment(UpdateCboardCommentDto dto) {
		CboardComment updatecomment = cboardcommentRepository.findById(dto.getCcommentId()).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id = " + dto.getCcommentId()));
		updatecomment.setCcommentContent(dto.getCcommentContent());
		updatecomment.setCcommentTime(LocalDateTime.now());
		return cboardcommentRepository.save(updatecomment);
	}
	
	@Transactional
	public void deleteComment(Long ccommentId) {
		CboardComment deletecomment = cboardcommentRepository.findById(ccommentId).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다" + ccommentId));;
		cboardcommentRepository.delete(deletecomment);
	}
	
}
