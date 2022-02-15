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

import com.ssafy.dtod.dto.RegistCboardCommentDto;
import com.ssafy.dtod.dto.UpdateCboardCommentDto;
import com.ssafy.dtod.model.CboardComment;
import com.ssafy.dtod.model.CommonBoard;
import com.ssafy.dtod.service.CboardCommentService;
import com.ssafy.dtod.service.CommonBoardService;
import com.ssafy.dtod.service.UserService;

@RestController
@RequestMapping("/api/ccomment")
public class CboardCommentContorller {
	
	@Autowired
	CboardCommentService cboardcommentService;
	@Autowired
	UserService userService;
	@Autowired
	CommonBoardService commonboardService;
	
	@GetMapping("/list/{cboardId}")
	ResponseEntity<List<CboardComment>> listComment(@PathVariable Long cboardId){
		CommonBoard commonboard = commonboardService.findByBoardId(cboardId);
		return ResponseEntity.ok(cboardcommentService.listComment(commonboard));
	}
	
	@PostMapping("/regist/{cboardId}")
	public ResponseEntity<CboardComment> registComment(@RequestBody RegistCboardCommentDto dto, @PathVariable Long cboardId){
		dto.setUser(userService.getMyUserWithAuthorities().get());
		dto.setCommonboard(commonboardService.findByBoardId(cboardId));
		return ResponseEntity.ok(cboardcommentService.registComment(dto));
	}
	
	@PutMapping("/update/{cboardId}")
	public ResponseEntity<CboardComment> updateComment(@RequestBody UpdateCboardCommentDto dto, @PathVariable Long cboardId){
		dto.setUser(userService.getMyUserWithAuthorities().get());
		dto.setCommonboard(commonboardService.findByBoardId(cboardId));
		return ResponseEntity.ok(cboardcommentService.updateComment(dto));
	}
	
	@DeleteMapping("/delete/{ccommentId}")
	public void deleteComment(@PathVariable Long ccommentId) {
		cboardcommentService.deleteComment(ccommentId);
	}
	
}
