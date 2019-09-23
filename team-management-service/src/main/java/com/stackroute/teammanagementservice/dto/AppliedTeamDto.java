package com.stackroute.teammanagementservice.dto;

import lombok.*;
import org.springframework.stereotype.Component;

/**With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods*/
@Data

/**@NoArgsConstructor will generate constructor with no arguments*/
@NoArgsConstructor

/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor


@Builder
@ToString
@Component
public class AppliedTeamDto {
    private String email;
    private String ideaTitle;
}
