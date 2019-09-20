package com.stackroute.teammanagementservice.dto;

import com.stackroute.teammanagementservice.domain.Role;
import com.stackroute.teammanagementservice.domain.RoleSp;
import lombok.*;

@Data

/**@NoArgsConstructor will generate constructor with no arguments*/
@NoArgsConstructor

/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor
@Builder
@ToString
public class ServiceProviderDto {

    private String name;
    private String email;
    private String mobileNo;
    private RoleSp role;
    private String chargePerHour;
}
