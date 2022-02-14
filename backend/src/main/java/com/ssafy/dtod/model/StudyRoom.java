package com.ssafy.dtod.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "studyroom")
public class StudyRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id")
	private Long roomId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@Column(name = "room_title", length = 20)
	private String roomTitle;
	
	@Column(name = "room_pwd", length = 20)
	private String roomPwd;
	
	@Column(name = "room_person")
	private Integer roomPerson;
	
	@Column(name = "room_techstack", length = 100)
	private String roomTechstack;
	
	@Column(name = "room_img", length = 100)
	private String roomImg;
	
	@Column(name = "room_ingdate", length = 30)
	private String roomIngdate;
	
	@Column(name = "room_ingday", length = 20)
	private String roomIngday;
	
	@Column(name = "room_content1", columnDefinition = "TEXT")
	private String roomContent1;
	
	@Column(name = "room_content2", columnDefinition = "TEXT")
	private String roomContent2;
	
	@Column(name = "room_time")
	private LocalDate roomTime;
}
