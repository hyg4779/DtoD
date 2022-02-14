package com.ssafy.dtod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.dtod.model.CheckIn;

public interface CheckInRepository extends JpaRepository<CheckIn, Long>{
//	CheckIn findByUser(User user);
}
