package com.stackroute.repository;

import com.stackroute.domain.ServiceProvider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Repository marks the specific class as a Data Access Object
 */
@Repository
public interface ServiceProviderRepository extends MongoRepository<ServiceProvider,String> {
  /**findByEmail method to get Serviceprovider by its email*/
    public ServiceProvider findByEmail(String email);
}
