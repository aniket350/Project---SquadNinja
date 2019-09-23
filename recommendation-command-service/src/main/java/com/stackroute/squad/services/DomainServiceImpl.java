package com.stackroute.squad.services;

import com.stackroute.squad.domain.Domain;
import com.stackroute.squad.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
public class DomainServiceImpl implements DomainService {
  DomainRepository domainRepository;

  /**
   * Constructor based Dependency injection to inject Domain Repository here
   */
  @Autowired
  public DomainServiceImpl(DomainRepository domainRepository) {
    this.domainRepository = domainRepository;
  }

  /**
   * Implementation of the method to save the domain
   */
  @Override
  public Domain saveDomain(Domain domain) {
    Domain savedomain = domainRepository.save(domain);
    return savedomain;
  }

  /**
   * Implementation of the method to get all the domains
   */
  @Override
  public List<Domain> getAllDomains() {
    List<Domain> allDomains = (List<Domain>) domainRepository.findAll();
    return allDomains;

  }

  /**
   * Implementation of the method to update the domain
   */
  @Override
  public Domain updateDomain(Domain domain) {
    Domain updateDomain = domainRepository.save(domain);
    return updateDomain;

  }
}
