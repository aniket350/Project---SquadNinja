package com.stackroute.recommendedqueryservice.domain;

import lombok.*;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

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
public class ServiceProvider {
    /**
     * Id annotation makes id variable as Primary key
     */
    @Id
    private Long id;
    private String name;
    private String mobileNo;
    private String email;
    private String domain;
    private String subDomain;
    private String previousProject;
    private String chargePerHour;
    private String currentLocation;
     private List<String> preferredLocation;
    @Relationship(type = "has_skills", direction = Relationship.OUTGOING)
    private List<Skills> skills;
}
