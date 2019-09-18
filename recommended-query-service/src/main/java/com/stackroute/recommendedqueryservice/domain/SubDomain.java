package com.stackroute.recommendedqueryservice.domain;

import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

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
public class SubDomain {
    /**
     * Id annotation makes id variable as Primary key
     */
    @Id @GeneratedValue
    private Long id;
    private String subDomainName;
    @Relationship(type = "has", direction = Relationship.INCOMING)
    private Domain domain;
}
