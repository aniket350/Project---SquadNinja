package com.stackroute.squad.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.stackroute.squad.domain.Roles;
import com.stackroute.squad.domain.Skills;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/*@NodeEntity will be represented as nodes in the graph.*/
/*it can generate getters and setters for those object automatically by using Lombok annotations. The easiest way is to use the @Data annotation.*/
@Data
/*It generates constructor with no parameters*/
@NoArgsConstructor
/*It generates a constructor with 1 parameter for each field*/
@AllArgsConstructor
@Getter
@Setter
@ToString
@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = ServiceProviderDto.class)
public class ServiceProviderDto {
  private String email;
  private String name;
  private String mobileNo;
  private String domain;
  private String subDomain;
  private Role role;
  private List<IdeaDto> workedIdeas;
  private List<IdeaDto> invitedIdeas;
  private String chargePerHour;
  private String currentLocation;
  private List<String> preferredLocation;
  private Date timestamp;
}
