package com.stackroute.squad.services;


import com.stackroute.squad.domain.ServiceProvider;
import com.stackroute.squad.exceptions.ServiceProviderAlreadyExistException;

import java.util.List;

public interface ServiceProviderService {
  /**
   * AbstractMethod to save a serviceProvider
   */
  public ServiceProvider save(ServiceProvider serviceProvider) throws Exception;

  /**
   * AbstractMethod to get all serviceProvider
   */
  public List<ServiceProvider> getAllServiceProvider() throws ServiceProviderAlreadyExistException;

  /**
   * AbstractMethod to update serviceProvider
   */
  public ServiceProvider updateServiceProvider(ServiceProvider serviceProvider) throws ServiceProviderAlreadyExistException;

  /**
   * AbstractMethod to get serviceProvider by email
   */
  public ServiceProvider getByEmail(String email) throws ServiceProviderAlreadyExistException;

}
