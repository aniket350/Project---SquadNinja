package com.stackroute.squad.services;

import com.stackroute.squad.domain.SubDomain;
import com.stackroute.squad.repository.SubDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
public class SubDomainServiceImpl implements SubDomainService {
  SubDomainRepository subDomainRepository;

  /**
   * Constructor based Dependency injection to inject subdomain Repository here
   */
  @Autowired
  public SubDomainServiceImpl(SubDomainRepository subDomainRepository) {
    this.subDomainRepository = subDomainRepository;
  }

  /**
   * Implementation of saving the subdomain
   */
  @Override
  public SubDomain save(SubDomain subDomain) {
    return subDomainRepository.save(subDomain);
  }

  /**
   * Implementation of hte method to get All the subdomains
   */
  @Override
  public List<SubDomain> getAllSubDomains() {
    List<SubDomain> allSubDomain = (List<SubDomain>) subDomainRepository.findAll();
    return allSubDomain;
  }

  /**
   * Implementation method to update the subdomain*/
  @Override
  public SubDomain updateSubDomain(SubDomain subDomain) {
    SubDomain updateSubDomain = subDomainRepository.save(subDomain);
    return updateSubDomain;
  }
}
