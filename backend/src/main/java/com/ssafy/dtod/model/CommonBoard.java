package com.ssafy.dtod.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@Table(name = "commonboard")
// 자유게시판, 질문게시판
public class CommonBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cboard_id")
	private Long cboardId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@Column(name = "cboard_type", length = 20)
	private String cboardType;
	
	@Column(name = "cboard_title", length = 20)
	private String cboardTitle;
	
	@Column(name = "cboard_code", columnDefinition = "TEXT")
	private String cboardCode;
	
	@Column(name = "cboard_content", columnDefinition = "TEXT")
	private String cboardContent;
	
	@Column(name = "cboard_techstack", length = 100)
	private String cboardTechstack;
	
	@Column(name = "cboard_img", length = 100)
	private String cboardImg;
}
