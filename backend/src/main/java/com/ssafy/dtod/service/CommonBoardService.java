package com.ssafy.dtod.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dtod.dto.RegistCommonBoardDto;
import com.ssafy.dtod.dto.ViewCommonBoardDto;
import com.ssafy.dtod.model.CommonBoard;
import com.ssafy.dtod.repository.CommonBoardRepository;

@Service
public class CommonBoardService {
	
	@Autowired
	private CommonBoardRepository commonboardRepository;
	
	@Transactional
	public List<CommonBoard> listBoard(){
		return commonboardRepository.findAll();
	}
	
	@Transactional
	public Optional<CommonBoard> viewBoard(Long cboardId){
		return commonboardRepository.findById(cboardId);
	}
	
	@Transactional
	public CommonBoard registBoard(RegistCommonBoardDto dto) {
		CommonBoard commonboard = CommonBoard.builder()
				.cboardType(dto.getCboardType())
				.cboardTitle(dto.getCboardTitle())
				.cboardCode(dto.getCboardCode())
				.cboardContent(dto.getCboardContent())
				.cboardTechstack(String.join(",", dto.getCboardTechstack()))
				.cboardImg(dto.getCboardImg())
				.user(dto.getUser())
				.build();
		return commonboardRepository.save(commonboard);
	}
	
	@Transactional
	public CommonBoard updateBoard(ViewCommonBoardDto dto) {
		CommonBoard updateboard = commonboardRepository.findById(dto.getCboardId()).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + dto.getCboardId()));
		updateboard.setCboardType(dto.getCboardType());
		updateboard.setCboardTitle(dto.getCboardTitle());
		updateboard.setCboardCode(dto.getCboardCode());
		updateboard.setCboardContent(dto.getCboardContent());
		updateboard.setCboardTechstack(String.join(",", dto.getCboardTechstack()));
		
		return commonboardRepository.save(updateboard);
	}
	
	@Transactional
	public void deleteBoard(Long cboardId) {
		CommonBoard deleteboard = commonboardRepository.findById(cboardId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + cboardId));
		commonboardRepository.delete(deleteboard);
	}
	
	@Transactional
	public CommonBoard findByBoardId(Long cboardId) {
		return commonboardRepository.findById(cboardId).get();
	}
	
}
