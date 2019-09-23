package com.stackroute.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

//Idea domain class

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Idea {

    private String id;
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

}
