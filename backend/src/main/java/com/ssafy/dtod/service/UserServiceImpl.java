package com.ssafy.dtod.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dtod.model.User;
import com.ssafy.dtod.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	//회원가입
	public User Save(User user) {
		userRepository.save(user);
		return user;
	}
	
	//회원 전체 조회
	public List<User> findAll(){
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(u -> users.add(u));
		return users;
	}
	
	//ID로 회원 조회
	public Optional<User> findById(String userId){
		Optional<User> user = userRepository.findById(userId);
		return user;
	}
	
	//ID로 회원 삭제
	public void deleteById(String userId) {
		userRepository.deleteById(userId);
	}
	
	//ID로 회원 수정
	public void updateById(String userId, User updatedUser) {
	        Optional<User> user = userRepository.findById(userId);
	        if (user.isPresent()) {
	            user.get().setUserPw(updatedUser.getUserPw());
	            user.get().setUserName(updatedUser.getUserName());
	            user.get().setUserTechstack(updatedUser.getUserTechstack());
	            user.get().setUserGrade(updatedUser.getUserGrade());
	            userRepository.save(user.get());
	        }
	    }
	
}
