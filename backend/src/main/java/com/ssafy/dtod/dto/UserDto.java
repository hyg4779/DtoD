package com.ssafy.dtod.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
 
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
 
   @NotNull
   @Size(min = 3, max = 50)
   private String userEmail;
 
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @NotNull
   @Size(min = 3, max = 100)
   private String userPwd;
 
   @NotNull
   @Size(min = 2, max = 10)
   private String userName;
   
   //@NotNull
   //@Size(min = 1, max = 100)
   private List<String> userTechstack;
   
   @NotNull
   @Size(min = 1, max = 50)
   private String userJobs;
}