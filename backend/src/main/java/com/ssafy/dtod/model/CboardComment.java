package com.ssafy.dtod.model;

import java.time.LocalDateTime;

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
@Table(name = "cboardcomment")
public class CboardComment {
		// 댓글 번호
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "ccomment_id")
		private Long ccommentId;
		
		// 댓글 게시물 번호(외래키 설정)
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "cboard_id")
		@OnDelete(action = OnDeleteAction.CASCADE)
		private CommonBoard commonboard;
		
		// 댓글 유저 번호(외래키 설정)
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "user_id")
		@OnDelete(action = OnDeleteAction.CASCADE)
		private User user;
		
		@Column(name = "ccomment_content", length = 50)
		private String ccommentContent;
		
		@Column(name = "ccomment_time")
		private LocalDateTime ccommentTime;
}
