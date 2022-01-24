package com.ssafy.dtod.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ssafy.dtod.model.User;

@Component
public interface UserService {
	
	User Save(User user);
	List<User> findAll();
	public Optional<User> findById(String userId);
	void deleteById(String userId);
	void updateById(String userId, User user);
	
	
}
