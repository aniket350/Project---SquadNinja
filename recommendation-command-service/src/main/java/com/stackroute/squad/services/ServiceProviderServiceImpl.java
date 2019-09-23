package com.stackroute.squad.services;

import com.stackroute.squad.domain.ServiceProvider;
import com.stackroute.squad.dto.AppliedTeamDto;
import com.stackroute.squad.dto.IdeaDto;
import com.stackroute.squad.dto.ServiceProviderDto;
import com.stackroute.squad.dto.WorkedTeamDto;
import com.stackroute.squad.exceptions.ServiceProviderAlreadyExistException;
import com.stackroute.squad.exceptions.ServiceProviderNotFoundException;
import com.stackroute.squad.repository.ServiceProviderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
public class ServiceProviderServiceImpl implements ServiceProviderService {
  ServiceProviderRepository serviceProviderRepository;

  /**
   * Constructor based Dependency injection to inject serviceprovider Repository here
   */
  @Autowired
  public ServiceProviderServiceImpl(ServiceProviderRepository serviceProviderRepository) {
    this.serviceProviderRepository = serviceProviderRepository;
  }

  /**
   * Implementation of save Service provider method
   */
  @Override
  public ServiceProvider save(ServiceProvider serviceProvider) throws ServiceProviderAlreadyExistException {
    return serviceProviderRepository.save(serviceProvider);
  }

  /*It will listen the data from spProfile queue*/
  @RabbitListener(queues = "${spProfile.rabbitmq.queue}")
  public void receiveData(ServiceProviderDto serviceProviderDto) throws ServiceProviderAlreadyExistException {

    ServiceProvider serviceProvider = new ServiceProvider();
    if (serviceProviderRepository.findByEmail(serviceProvider.getEmail()) != null) {

      throw new ServiceProviderAlreadyExistException("serviceprovider already exist");
    }
    serviceProvider.setChargePerHour(serviceProviderDto.getChargePerHour());
    serviceProvider.setDomain(serviceProviderDto.getDomain());
    serviceProvider.setEmail(serviceProviderDto.getEmail());
    serviceProvider.setMobileNo(serviceProviderDto.getMobileNo());
    serviceProvider.setName(serviceProviderDto.getName());
    serviceProvider.setPreferredLocation(serviceProviderDto.getPreferredLocation());
    serviceProvider.setCurrentLocation(serviceProviderDto.getCurrentLocation());
    serviceProviderRepository.save(serviceProvider);
  }

  /*It will listen the data from spProfile queue*/
  @RabbitListener(queues = "${spUpdate.rabbitmq.queue}")
  public void updatedServiceProvider(ServiceProviderDto serviceProviderDto) throws ServiceProviderNotFoundException {


    ServiceProvider retrievedServiceProvider = serviceProviderRepository.findByEmail(serviceProviderDto.getEmail());
    if (serviceProviderRepository.findByEmail(retrievedServiceProvider.getEmail()) != null) {

      throw new ServiceProviderNotFoundException("serviceprovider not found");
    }
    retrievedServiceProvider.setChargePerHour(serviceProviderDto.getChargePerHour());
    retrievedServiceProvider.setDomain(serviceProviderDto.getDomain());
    retrievedServiceProvider.setMobileNo(serviceProviderDto.getMobileNo());
    retrievedServiceProvider.setName(serviceProviderDto.getName());
    retrievedServiceProvider.setPreferredLocation(serviceProviderDto.getPreferredLocation());
    retrievedServiceProvider.setCurrentLocation(serviceProviderDto.getCurrentLocation());
    serviceProviderRepository.save(retrievedServiceProvider);
    System.out.println(serviceProviderDto.getRole());
    serviceProviderRepository.setPlayedByRelation(serviceProviderDto.getEmail(), serviceProviderDto.getRole().getRole().toLowerCase());
    for (int i = 0; i < serviceProviderDto.getRole().getSkills().size(); i++) {
      serviceProviderRepository.setHasSkillsRelation(serviceProviderDto.getEmail(), serviceProviderDto.getRole().getSkills().get(i).toLowerCase());
    }


  }

  @RabbitListener(queues = "${appliedTeam.rabbitmq.queue}")
  public void appliedTeam(AppliedTeamDto appliedTeamDto) throws ServiceProviderAlreadyExistException {
    ServiceProvider serviceProviderData = serviceProviderRepository.findByEmail(appliedTeamDto.getEmail());
    IdeaDto ideaDto1 = new IdeaDto();
    serviceProviderRepository.setAppliedForRelation(appliedTeamDto.getEmail(), appliedTeamDto.getIdeaTitle());

  }
  @RabbitListener(queues = "${workedOn.rabbitmq.queue}")
  public void workedOn(WorkedTeamDto workedTeamDto) throws ServiceProviderAlreadyExistException {
    ServiceProvider serviceProviderData1 = serviceProviderRepository.findByEmail(workedTeamDto.getEmail());
    IdeaDto ideaDto = new IdeaDto();
    serviceProviderRepository.setAppliedForRelation(workedTeamDto.getEmail(), workedTeamDto.getIdeaTitle());

  }

  /**
   * Implementation of get All Service provider method
   */

  @Override
  public List<ServiceProvider> getAllServiceProvider() throws ServiceProviderAlreadyExistException {
    List<ServiceProvider> allServiceProviders = (List<ServiceProvider>) serviceProviderRepository.findAll();
    return allServiceProviders;

  }

  /**
   * Implementation of update Service provider method
   */
  @Override
  public ServiceProvider updateServiceProvider(ServiceProvider serviceProvider) throws ServiceProviderAlreadyExistException {
    ServiceProvider updateServiceProvider = serviceProviderRepository.save(serviceProvider);
    return updateServiceProvider;

  }

  /**
   * Implementation of get Service provider by email method
   */
  @Override
  public ServiceProvider getByEmail(String email) throws ServiceProviderAlreadyExistException {
    return serviceProviderRepository.findByEmail(email);
  }
}
