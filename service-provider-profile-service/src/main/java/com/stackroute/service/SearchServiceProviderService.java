package com.stackroute.service;

import com.stackroute.domain.ServiceProvider;

import java.util.List;

public interface SearchServiceProviderService {
  /**
   * AbstractMethod to get the list of serviceproviders
   */
    List<ServiceProvider> searchListOfServiceProvider(String roleName);
}
