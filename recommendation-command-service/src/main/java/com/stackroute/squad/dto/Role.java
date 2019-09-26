package com.stackroute.squad.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.stereotype.Component;

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
public class Role {
  private String experience;
  private List<String> skills;
  private String role;

}
