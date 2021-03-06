package com.stackroute.recommendedqueryservice.service;

import com.stackroute.recommendedqueryservice.domain.ServiceProvider;
import com.stackroute.recommendedqueryservice.domain.Skills;
import com.stackroute.recommendedqueryservice.repository.TeamRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
public class RecommendedTeamServiceImpl implements RecommendedTeamService {
    private TeamRecommendationRepository teamRecommendationRepository;
    List<ServiceProvider> serviceProviders;

    /**
     * Constructor based Dependency injection to inject TeamRecommendationRepository here
     */
    @Autowired
    public RecommendedTeamServiceImpl(TeamRecommendationRepository teamRecommendationRepository, List<ServiceProvider> serviceProviders
    ) {
        this.teamRecommendationRepository = teamRecommendationRepository;
        this.serviceProviders =serviceProviders ;
    }
    /**
     * Implementation of getTeam method
     */
    @Override
    public List<ServiceProvider> getTeam(String title, String roleName) {
        List<ServiceProvider> serviceProvider = teamRecommendationRepository.findTeam(title, roleName.toLowerCase());
        return serviceProvider;
    }

    /**
     * Implementation of getTeamBasedOnAppliedIdea method
     */
    @Override
    public List<ServiceProvider> getTeamBasedOnAppliedIdea(String title, String roleName) {
        List<ServiceProvider> retrieveServiceProvider = teamRecommendationRepository.findTeamBasedOnAppliedIdea(title, roleName.toLowerCase());
        return retrieveServiceProvider;
    }
    /**
     * Implementation of getTeamBasedOnWorkedOnIdea method
     */
    @Override
    public List<ServiceProvider> getTeamBasedOnWorkedOnIdea(String title, String roleName) {
        System.out.println("rolename"+roleName);
        List<ServiceProvider> retrieveWorkedServiceProviders = teamRecommendationRepository.findTeamBasedOnWorkedOnIdea(title, roleName.toLowerCase());
        return retrieveWorkedServiceProviders;
    }
    /**
     * Implementation of getAutogeneratedTeam method
     */
    @Override
    public List<ServiceProvider> getAutogeneratedTeam(String title, String roleName) {
        List<ServiceProvider> serviceProvider = teamRecommendationRepository.findTeam(title, roleName.toLowerCase());
        List<ServiceProvider> retrieveAppliedServiceProvider = teamRecommendationRepository.findTeamBasedOnAppliedIdea(title, roleName.toLowerCase());
        List<ServiceProvider> retrieveWorkedServiceProvider = teamRecommendationRepository.findTeamBasedOnWorkedOnIdea(title, roleName.toLowerCase());
        serviceProviders.addAll(serviceProvider);
        for(int i=0;i<serviceProvider.size();i++){
            for(int j=0;j<retrieveAppliedServiceProvider.size();j++){
                String emailsp2=retrieveAppliedServiceProvider.get(j).getEmail();
            if(!serviceProvider.get(i).getEmail().equals(emailsp2)){
                serviceProviders.add(retrieveAppliedServiceProvider.get(j));
            }
            }
        }
       return  serviceProviders;    }

}
