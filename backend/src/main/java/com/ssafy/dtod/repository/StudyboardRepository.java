package com.ssafy.dtod.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.dtod.model.Studyboard;

public interface StudyboardRepository extends JpaRepository<Studyboard, Long>{

	Optional<Studyboard> save(Optional<Studyboard> updateboard);
	Studyboard findBysboardTechstack(String sboardTechstack);
}
