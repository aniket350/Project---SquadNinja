package com.stackroute.recommendedqueryservice.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@NodeEntity

/**With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods*/
@Data

/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor

/**@NoArgsConstructor will generate constructor with no arguments*/
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Skills {
    /**
     * Id annotation makes id variable as Primary key
     */
    @Id
    private Long id;
    private String skillName;
}
