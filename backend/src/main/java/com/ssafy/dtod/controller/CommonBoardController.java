package com.ssafy.dtod.controller;

import java.util.List;
import java.util.Optional;

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

import com.ssafy.dtod.dto.RegistCommonBoardDto;
import com.ssafy.dtod.dto.ViewCommonBoardDto;
import com.ssafy.dtod.model.CommonBoard;
import com.ssafy.dtod.service.CommonBoardService;
import com.ssafy.dtod.service.UserService;

@RestController
@RequestMapping("/api/cboard")
public class CommonBoardController {
	
	@Autowired
	private CommonBoardService commonboardService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	public ResponseEntity<List<CommonBoard>> listBoard(){
		return ResponseEntity.ok(commonboardService.listBoard());
	}
	
	@GetMapping("/view/{cboardId}")
	public ResponseEntity<Optional<CommonBoard>> viewBoard(@PathVariable Long cboardId){
		return ResponseEntity.ok(commonboardService.viewBoard(cboardId));
	}
	
	@PostMapping("/regist")
	public ResponseEntity<CommonBoard> registBoard(@RequestBody RegistCommonBoardDto dto){
		dto.setUser(userService.getMyUserWithAuthorities().get());
		return ResponseEntity.ok(commonboardService.registBoard(dto));
	}
	
	@PutMapping("/update")
	public ResponseEntity<CommonBoard> updateBoard(@RequestBody ViewCommonBoardDto dto){
		dto.setUser(userService.getMyUserWithAuthorities().get());
		return ResponseEntity.ok(commonboardService.updateBoard(dto));
	}
	
	@DeleteMapping("/delete/{cboardId}")
	public void deleteBoard(@PathVariable Long cboardId) {
		commonboardService.deleteBoard(cboardId);
	}
}
