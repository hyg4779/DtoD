package com.ssafy.dtod.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.dtod.model.SboardComment;
import com.ssafy.dtod.model.Studyboard;

public interface SboardCommentRepository extends JpaRepository<SboardComment, Long>{

	@Query("select c from SboardComment c where c.studyboard =:studyboard order by c.scommentId")
	List<SboardComment> findByStudyboard(@Param("studyboard") Studyboard studyboard);
	
}
