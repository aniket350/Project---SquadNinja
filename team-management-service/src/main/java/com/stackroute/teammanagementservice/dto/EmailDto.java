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
public class EmailDto {
    //@Value("${email.subject}")
    private String subject = "Squad Ninja";
    private String title;
    private String to;
   // @Value("${email.from}")
    private String from ="squadninjasr@gmail.com";
    private String body;
}
