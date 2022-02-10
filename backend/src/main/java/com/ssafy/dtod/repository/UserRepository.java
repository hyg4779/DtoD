package com.ssafy.dtod.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.dtod.model.User;
 
public interface UserRepository extends JpaRepository<User, Long> {
   @EntityGraph(attributePaths = "authorities")
   Optional<User> findOneWithAuthoritiesByUserEmail(String UserEmail);
   boolean existsByUserEmail(String userEmail);
   boolean existsByUserName(String userName);
}