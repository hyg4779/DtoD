package com.ssafy.dtod.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.dtod.model.CboardComment;
import com.ssafy.dtod.model.CommonBoard;

public interface CboardCommentRepository extends JpaRepository<CboardComment, Long>{

	
	@Query("select c from CboardComment c where c.commonboard =:commonboard order by c.ccommentId")
	List<CboardComment> findByCommonBoard(@Param("commonboard") CommonBoard commonboard);
}
