package com.stackroute.teammanagementservice.domain;

import lombok.*;

import java.util.List;

@Data

/**@NoArgsConstructor will generate constructor with no arguments*/
@NoArgsConstructor

/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor
@Builder
@ToString
public class RoleSp {

    private String role;
    private String experience;
    private List<String> skills;
}
