package com.ssafy.dtod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dtod.dto.RegistSboardCommentDto;
import com.ssafy.dtod.dto.UpdateSboardCommentDto;
import com.ssafy.dtod.model.SboardComment;
import com.ssafy.dtod.model.Studyboard;
import com.ssafy.dtod.service.SboardCommentService;
import com.ssafy.dtod.service.StudyboardService;
import com.ssafy.dtod.service.UserService;

@RestController
@RequestMapping("/api/scomment")
public class SboardCommentController {
	
	@Autowired
	private SboardCommentService sboardcommentService;
	@Autowired
	private StudyboardService studyboardService;
	@Autowired
	private UserService userService;
	
	//댓글 목록 조회
	@GetMapping("/list/{sboardId}")
	public ResponseEntity<List<SboardComment>> listComment(@PathVariable Long sboardId){
		Studyboard studyboard = studyboardService.findByBoardId(sboardId);
		
		return ResponseEntity.ok(sboardcommentService.listComment(studyboard));
	}
	
	//댓글 등록
	@PostMapping("/regist/{sboardId}")
	public ResponseEntity<SboardComment> registComment(@RequestBody RegistSboardCommentDto registsboardcommentDto, @PathVariable Long sboardId) {
		registsboardcommentDto.setUser(userService.getMyUserWithAuthorities().get());
		registsboardcommentDto.setStudyboard(studyboardService.findByBoardId(sboardId));
		return ResponseEntity.ok(sboardcommentService.registComment(registsboardcommentDto));
	}
	
	//댓글 수정
	@PutMapping("/update/{sboardId}")
	public ResponseEntity<SboardComment> updateComment(@RequestBody UpdateSboardCommentDto updatesboardcommentDto, @PathVariable Long sboardId) {
		updatesboardcommentDto.setUser(userService.getMyUserWithAuthorities().get());
		updatesboardcommentDto.setStudyboard(studyboardService.findByBoardId(sboardId));
		return ResponseEntity.ok(sboardcommentService.updateComment(updatesboardcommentDto));
	}
	
	//댓글 삭제
	@DeleteMapping("/delete/{scommentId}")
	public void deleteComment(@PathVariable Long scommentId) {
		sboardcommentService.deleteComment(scommentId);
	}

}
