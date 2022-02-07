package com.ssafy.dtod.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor // 파라미터가 없는 기본 생성자 생성
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자
@Entity
@Table(name = "studyboard")
public class Studyboard {

	//게시글 번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sboard_id")
	private long sboardId;
	
	//작성자 id(외래키 설정)
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	//게시글 제목
	@Column(name = "sboard_title", length = 20)
	private String sboardTitle;
	
	@Column(name = "sboard_content1", columnDefinition = "TEXT")
	private String sboardContent1;
	
	@Column(name = "sboard_content2", columnDefinition = "TEXT")
	private String sboardContent2;
	
	@Column(name = "sboard_content3", columnDefinition = "TEXT")
	private String sboardContent3;
	
	@Column(name = "sboard_techstack", length = 100)
	private String sboardTechstack;
	
//	@CreatedDate
//	@Column(name = "sboard_createddate")
//	private LocalDateTime sboardCreateddate;
//	
//	@LastModifiedDate
//	@Column(name = "sboard_modifieddate")
//	private LocalDateTime sboardModifieddate;
	

	  
}
