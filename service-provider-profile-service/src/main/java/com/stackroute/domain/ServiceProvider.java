package com.stackroute.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods
 */
@Data
// It generates constructor with no parameters
@NoArgsConstructor
// It generates a constructor with 1 parameter for each field
@AllArgsConstructor
//setter is a method that updates value of a variable
@Setter
//getter is a method that reads value of a variable
@Getter
@ToString
/*  It produces complex builder APIs for class
    It automatically produce the code required to have your class be instantiable with code
 */
@Builder

/**
 * Document annotated class will have the ability to represent objects in the database
 */
@Document(collection = "ServiceProvider")
public class ServiceProvider {


    @Id
    private String email;
    private String name;
    private String mobileNo;
    private String domain;
    private String subDomain;
    private Role role;
    private List<Idea> workedIdeas;
    private List<Idea> invitedIdeas;
    private String chargePerHour;
    private String currentLocation;
    private List<String> preferredLocation;
    private Date timestamp;


}
