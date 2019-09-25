package com.stackroute.service;


import com.stackroute.domain.Idea;
import com.stackroute.domain.Role;
import com.stackroute.domain.SearchServiceProvider;
import com.stackroute.domain.ServiceProvider;
import com.stackroute.dto.InvitedIdeaDto;
import com.stackroute.dto.ServiceProviderDto;
import com.stackroute.exception.UserAlreadyFoundException;
import com.stackroute.repository.SearchServiceProviderRepository;
import com.stackroute.repository.ServiceProviderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
public class ServiceProviderServiceImpl implements ServiceProviderService {

  private ServiceProviderRepository serviceProviderRepository;
  private SearchServiceProviderRepository searchServiceProviderRepository;
  private RabbitTemplate rabbitTemplate;

  /**
   * Constructor based Dependency injection to inject TrackRepository here
   */
  @Autowired
  public ServiceProviderServiceImpl(ServiceProviderRepository serviceProviderRepository, RabbitTemplate rabbitTemplate, SearchServiceProviderRepository searchServiceProviderRepository) {
    this.serviceProviderRepository = serviceProviderRepository;
    this.searchServiceProviderRepository = searchServiceProviderRepository;
    this.rabbitTemplate = rabbitTemplate;
  }

  public ServiceProviderServiceImpl() {
  }

  /*@Value annotation is found on a method, Spring context will invoke
    it when all the spring configurations and beans are getting loaded. */
  @Value("${spRegister.rabbitmq.exchange}")
  public String exchange;

  @Value("${spRegister.rabbitmq.routingkey}")
  public String routingkey;

  @Value("${spProfile.rabbitmq.exchange}")
  public String profileExchange;

  @Value("${spProfile.rabbitmq.routingkey}")
  public String profilRoutingkey;

  //for sending updated profile data to recommendation
  @Value("${spUpdate.rabbitmq.exchange}")
  String updateExchange;

  @Value("${spUpdate.rabbitmq.routingkey}")
  String updateRoutingKey;


  /*
   * Implementation of saving the serviceProvider
   */

  @Override
  public ServiceProvider saveServiceProvider(ServiceProviderDto provider) throws UserAlreadyFoundException {
    //for registering the new  serviceProvider if the emailId of the serviceProvider already exist it throws an exception like ServiceProviderAlreadyExistException

    ServiceProvider sp = new ServiceProvider();
    if (serviceProviderRepository.findByEmail(provider.getEmail()) != null) {
      throw new UserAlreadyFoundException("User already Exists");
    } else {

      sp.setName(provider.getUserName());
      sp.setEmail(provider.getEmail());
      //for recommendation
      rabbitTemplate.convertAndSend(exchange, routingkey, provider);
      rabbitTemplate.convertAndSend(profileExchange, profilRoutingkey, sp);
      return serviceProviderRepository.save(sp);
    }

  }

  @Override
  public ServiceProvider getTheProfile(String email) {

    return serviceProviderRepository.findByEmail(email);
  }


  @Override
  public ServiceProvider updateTheProfile(ServiceProvider provider) {


    ServiceProvider serviceProvider = serviceProviderRepository.findByEmail(provider.getEmail());

    serviceProvider.setName(provider.getName());
    serviceProvider.setMobileNo(provider.getMobileNo());
    serviceProvider.setDomain(provider.getDomain());
    serviceProvider.setSubDomain(provider.getSubDomain());
    serviceProvider.setChargePerHour(provider.getChargePerHour());
    serviceProvider.setCurrentLocation(provider.getCurrentLocation());
    serviceProvider.setPreferredLocation(provider.getPreferredLocation());
    Role role = new Role();
    role.setRole(provider.getRole().getRole());
    role.setExperience(provider.getRole().getExperience());
    role.setSkills(provider.getRole().getSkills());
    serviceProvider.setRole(role);
    ServiceProvider updateServiceProvider = serviceProviderRepository.save(serviceProvider);

    saveForSearch(serviceProvider);

    rabbitTemplate.convertAndSend(updateExchange, updateRoutingKey, serviceProvider);
    return updateServiceProvider;
  }


  public void saveForSearch(ServiceProvider serviceProvider) {

    List<ServiceProvider> serviceProviderList;
    SearchServiceProvider fetchedSearchServiceProvider = searchServiceProviderRepository.findByRoleName(serviceProvider.getRole().getRole());
    if (fetchedSearchServiceProvider != null) {

      serviceProviderList = fetchedSearchServiceProvider.getServiceProviderList();
      serviceProviderList.add(serviceProvider);
      fetchedSearchServiceProvider.setServiceProviderList(serviceProviderList);
      searchServiceProviderRepository.save(fetchedSearchServiceProvider);

    } else if (fetchedSearchServiceProvider == null) {

      SearchServiceProvider searchServiceProvider = new SearchServiceProvider();
      searchServiceProvider.setRoleName(serviceProvider.getRole().getRole());
      List<ServiceProvider> list = new ArrayList<>();
      list.add(serviceProvider);
      searchServiceProvider.setServiceProviderList(list);
      searchServiceProviderRepository.save(searchServiceProvider);

    }

  }

  @RabbitListener(queues = "${invitedIdea.rabbitmq.queue}")
  public void saveInvitedIdeas(InvitedIdeaDto idea) {


    List<Idea> invitedIdeas;
    ServiceProvider serviceProvider = serviceProviderRepository.findByEmail(idea.getInviteeEmailId());
    if (serviceProvider.getInvitedIdeas() == null) {
      invitedIdeas = new ArrayList<>();
    } else {
      invitedIdeas = serviceProvider.getInvitedIdeas();
    }

    Idea inviteIdea = new Idea();
    inviteIdea.setTitle(idea.getTitle());
    inviteIdea.setDescription(idea.getDescription());
    inviteIdea.setDuration(idea.getDuration());
    inviteIdea.setDomain(idea.getDomain());
    inviteIdea.setSubDomain(idea.getSubDomain());
    inviteIdea.setCost(idea.getCost());
    inviteIdea.setStatus(idea.getStatus());
    inviteIdea.setPostedBy(idea.getPostedBy());
    inviteIdea.setPostedOn(idea.getPostedOn());
//    inviteIdea.setRole(idea.getRole());
    inviteIdea.setLocation(idea.getLocation());

    invitedIdeas.add(inviteIdea);
    serviceProvider.setInvitedIdeas(invitedIdeas);
    serviceProviderRepository.save(serviceProvider);

  }

}
