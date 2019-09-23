package com.stackroute.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

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
@Document(collection = "SearchServiceProvider")
public class SearchServiceProvider {

  @Id
  private String roleName;
  private List<ServiceProvider> serviceProviderList;
}
