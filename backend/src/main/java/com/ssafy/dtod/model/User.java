package com.ssafy.dtod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Set;
 
@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
 
   @JsonIgnore
   @Id
   @Column(name = "user_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long userId;
 
   @Column(name = "user_email", length = 50, unique = true)
   private String userEmail;
 
   @JsonIgnore
   @Column(name = "user_pwd", length = 100)
   private String userPwd;
 
   @Column(name = "user_name", length = 10)
   private String userName;
   
   @Column(name = "user_img", length = 150)
   private String userImg;
 
   @Column(name = "user_techstack", length = 100)
   private String userTechstack;
   
   @Column(name = "user_jobs", length = 50)
   private String userJobs;
  
   @JsonIgnore
   @Column(name = "user_activated")
   private boolean userActivated;

   // User와 Authority 테이블의 다대대 관계를 1:다, 다:1 관계의 조인 테이블로 정의
   @ManyToMany
   @JoinTable(
      name = "user_authority",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
   private Set<Authority> authorities;
}