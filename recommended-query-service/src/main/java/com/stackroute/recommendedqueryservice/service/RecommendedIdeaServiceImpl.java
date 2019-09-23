package com.stackroute.recommendedqueryservice.service;

import com.stackroute.recommendedqueryservice.domain.Domain;
import com.stackroute.recommendedqueryservice.domain.Idea;
import com.stackroute.recommendedqueryservice.domain.IdeaHamster;
import com.stackroute.recommendedqueryservice.domain.SubDomain;
import com.stackroute.recommendedqueryservice.repository.IdeaRecommendationRepository;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */

@Service
public class RecommendedIdeaServiceImpl implements RecommendedIdeaService {
    private IdeaRecommendationRepository ideaRecommendationRepository;

    /**
     * Constructor based Dependency injection to inject TrackRepository here
     */

    @Autowired
    public RecommendedIdeaServiceImpl(IdeaRecommendationRepository ideaRecommendationRepository) {
        this.ideaRecommendationRepository = ideaRecommendationRepository;
    }

    /**
     * Implementation of findBySkill method
     */

    @Override
    public List<Idea> findBySkill(String email) {
        return ideaRecommendationRepository.findBySkill(email);
    }

    /**
     * Implementation of findByRole method
     */

    @Override
    public List<Idea> findByRole(String email) {
        return ideaRecommendationRepository.findByRole(email);
    }

    /**
     * Implementation of findByWorkedOnIdea method
     */

    @Override
    public List<Idea> findByWorkedOnIdea(String email) {

        return ideaRecommendationRepository.findByWorkedOnIdea(email);
    }

    /**
     * Implementation of findByAppliedOnIdea method
     */

    @Override
    public List<Idea> findByAppliedOnIdea(String email) {
        return ideaRecommendationRepository.findByAppliedOnIdea(email);
    }


}
