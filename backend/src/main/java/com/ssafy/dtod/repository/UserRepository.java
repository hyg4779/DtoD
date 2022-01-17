package com.ssafy.dtod.repository;

import com.ssafy.dtod.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}