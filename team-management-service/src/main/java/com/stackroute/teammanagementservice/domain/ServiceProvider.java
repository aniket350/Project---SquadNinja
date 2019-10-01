package com.stackroute.teammanagementservice.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
/**@NoArgsConstructor will generate constructor with no arguments*/
@Data

/**@NoArgsConstructor will generate constructor with no arguments*/
@NoArgsConstructor

/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor
@Builder
@ToString
public class ServiceProvider {
    private String email;
    private String name;
    private String mobileNo;
    private String domain;
    private String subDomain;
    private RoleSp role;
    private List<Idea> workedIdeas;
    private List<Idea> invitedIdeas;
    private String chargePerHour;
    private String currentLocation;
    private List<String> preferredLocation;
    private Date timestamp;
}
