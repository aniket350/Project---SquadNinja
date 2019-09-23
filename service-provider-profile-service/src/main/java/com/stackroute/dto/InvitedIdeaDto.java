package com.stackroute.dto;

import com.stackroute.domain.RequiredRole;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class InvitedIdeaDto {

    private String title;
    private String description;
    private String duration;
    private String domain;
    private String subDomain;
    private String cost;
    private List<RequiredRole> role;
    private String status;
    private Date postedOn;
    private String postedBy;
    private String location;
    private String inviteeEmailId;
}
