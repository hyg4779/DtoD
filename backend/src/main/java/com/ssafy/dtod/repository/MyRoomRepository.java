package com.ssafy.dtod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.dtod.model.MyRoom;
import com.ssafy.dtod.model.User;

public interface MyRoomRepository extends JpaRepository<MyRoom, Long>{
	
	MyRoom findByUser(User user);

}
