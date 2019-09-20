package com.stackroute.recommendedqueryservice.domain;

import lombok.*;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity

/**With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods*/
@Data

/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor

/**@AllArgsConstructor will generate constructor with all properties in the class*/
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Roles {
    /**
     * Id annotation makes id variable as Primary key
     */
    @Id
    private Long id;
    private String role;
    private String noOfPeople;
    private String experience;
    @Relationship(type = "have", direction = Relationship.OUTGOING)
    private Skills skills;
    @Relationship(type="played_by", direction = Relationship.OUTGOING)
    private ServiceProvider serviceProvider;
}
