package com.stackroute.repository;

import com.stackroute.domain.SearchServiceProvider;
import com.stackroute.domain.ServiceProvider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/*@Repository annotation is used to indicate that the class provides the mechanism for storage, retrieval,
 search, update and delete operation on objects.*/
@Repository
public interface SearchServiceProviderRepository extends MongoRepository<SearchServiceProvider,Integer> {
  /**findByRoleName method to get serviceprovider by its roleName*/
    SearchServiceProvider findByRoleName(String roleName);
}
