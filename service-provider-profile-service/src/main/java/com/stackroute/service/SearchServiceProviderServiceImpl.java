package com.stackroute.service;

import com.stackroute.domain.SearchServiceProvider;
import com.stackroute.domain.ServiceProvider;
import com.stackroute.repository.SearchServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
  @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
public class SearchServiceProviderServiceImpl implements SearchServiceProviderService {

    private SearchServiceProviderRepository searchServiceProviderRepository;
  /*
        Constructor based Dependency injection to inject SearchServiceProviderRepository here
       */
    @Autowired
    public SearchServiceProviderServiceImpl(SearchServiceProviderRepository searchServiceProviderRepository) {
        this.searchServiceProviderRepository = searchServiceProviderRepository;
    }
  /*
        Implementation of searchListOfServiceProvider method
       */
    @Override
    public List<ServiceProvider> searchListOfServiceProvider(String roleName) {

        SearchServiceProvider serviceProviders = searchServiceProviderRepository.findByRoleName(roleName);
        return serviceProviders.getServiceProviderList();
    }
}
