package com.stackroute.recommendedqueryservice.controller;

import com.stackroute.recommendedqueryservice.domain.*;
import com.stackroute.recommendedqueryservice.service.RecommendedIdeaService;
import com.stackroute.recommendedqueryservice.service.RecommendedTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "*")
/**
 * RestController annotation is used to create Restful web services using Spring MVC
 */
@RestController
/**
 * RequestMapping annotation maps HTTP requests to handler methods
 */
@RequestMapping("api/v1/")
public class IdeaRecommendationController {
    private RecommendedIdeaService recommendedIdeaService;
    private RecommendedTeamService recommendedTeamService;

    /**
     * Constructor based Dependency injection to inject Recommended ideaService and recommendedTeamService into controller
     */

    @Autowired
    public IdeaRecommendationController(RecommendedIdeaService recommendedIdeaService, RecommendedTeamService recommendedTeamService) {
        this.recommendedIdeaService = recommendedIdeaService;
        this.recommendedTeamService = recommendedTeamService;
    }

    /**
     * by the get mapping it is used for getting the idea by the skills and by the email of specific serviceProvider
     */
    @GetMapping("skill/{email}")
    public ResponseEntity<Collection<Idea>> getIdeasBySkill(@PathVariable("email") String email) {
        List<Idea> Ideas = recommendedIdeaService.findBySkill(email);
        return new ResponseEntity<>(Ideas, HttpStatus.OK);
    }

    /**
     * by these get mapping it is used for getting the idea throw role and by giving the email of specific serviceProvider
     */
    @GetMapping("role/{email}")
    public ResponseEntity<Iterable<Idea>> getIdeasByRole(@PathVariable("email") String email) {
        Iterable<Idea> ideas = recommendedIdeaService.findByRole(email);
        return new ResponseEntity<>(ideas, HttpStatus.OK);
    }

    /**
     * by these get mapping it is used for getting the serviceprovider worked ideas by the email of specific serviceprovider
     */
    @GetMapping("workedonIdea/{email}")
    public ResponseEntity<Iterable<Idea>> getIdeasByPreviousWork(@PathVariable("email") String name) {
        Iterable<Idea> ideas = recommendedIdeaService.findByWorkedOnIdea(name);
        return new ResponseEntity<>(ideas, HttpStatus.OK);
    }

    /**
     * by these get mapping it is used for getting the serviceprovider appliedon ideas by the email of specific serviceprovider
     */
    @GetMapping("appliedonIdea/{email}")
    public ResponseEntity<Iterable<Idea>> getIdeasByPreviousApplied(@PathVariable("email") String email) {
        Iterable<Idea> ideas = recommendedIdeaService.findByAppliedOnIdea(email);
        return new ResponseEntity<>(ideas, HttpStatus.OK);
    }

    /**
     * by these get mapping it is used for getting the serviceprovider previously worked ideas by the title
     */
    @GetMapping("previouslyWorked/{title}")
    public ResponseEntity<?> getautoTeam(@PathVariable("title") String title, @RequestParam("roleName") String roleName) {
        List<ServiceProvider> serviceProviders = recommendedTeamService.getTeamBasedOnWorkedOnIdea(title, roleName);

        return new ResponseEntity<>(serviceProviders, HttpStatus.OK);
    }

    /**
     * by these get mapping it is used for getting the serviceprovider applied ideas by the title
     */
    @GetMapping("applied/{title}")
    public ResponseEntity<Collection<ServiceProvider>> getRecommendedTeam(@PathVariable("title") String title, @RequestParam("roleName") String roleName) {
        Collection<ServiceProvider> serviceProviders = recommendedTeamService.getTeam(title.toLowerCase(), roleName.toLowerCase());
        return new ResponseEntity<>(serviceProviders, HttpStatus.OK);
    }

}


