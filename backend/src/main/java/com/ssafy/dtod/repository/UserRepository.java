package com.ssafy.dtod.repository;

import com.ssafy.dtod.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
 
import java.util.Optional;
 
public interface UserRepository extends JpaRepository<User, Long> {
   @EntityGraph(attributePaths = "authorities")
   Optional<User> findOneWithAuthoritiesByUserEmail(String UserEmail);
}