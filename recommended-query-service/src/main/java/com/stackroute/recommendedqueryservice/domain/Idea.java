package com.stackroute.recommendedqueryservice.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.List;

@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")

/**With @NodeEntity, domain class can be set as node*/
@NodeEntity

/**With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods*/
@Data

/**@NoArgsConstructor will generate constructor with no arguments*/
@NoArgsConstructor

/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Idea {
    /**
     * Id annotation makes id variable as Primary key
     */
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private String duration;
    private String cost;
    private String status;
    private String postedBy;
    private Date postedOn;
    @Relationship(type = "belongs_to", direction = Relationship.OUTGOING)
    private SubDomain subDomain;
    @Relationship(type = "requires", direction = Relationship.OUTGOING)
    private List<Roles> role;
    @Relationship(type = "requires", direction = Relationship.OUTGOING)//experience
    private List<Skills> skills;
    @Relationship(type = "worked_on", direction = Relationship.INCOMING)
    private List<ServiceProvider> serviceProvider;

}
