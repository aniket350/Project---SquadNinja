package com.stackroute.teammanagementservice.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Data

/**@NoArgsConstructor will generate constructor with no arguments*/
@NoArgsConstructor

/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor


@Builder
@ToString
@Component
public class WorkedTeamDto {
    private String email;
    private String ideaTitle;
}
