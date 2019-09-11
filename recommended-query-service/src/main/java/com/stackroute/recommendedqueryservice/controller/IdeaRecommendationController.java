package com.stackroute.recommendedqueryservice.controller;

import com.stackroute.recommendedqueryservice.domain.*;
import com.stackroute.recommendedqueryservice.service.RecommendedIdeaService;
import com.stackroute.recommendedqueryservice.service.RecommendedeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class IdeaRecommendationController {
    private RecommendedIdeaService recommendedIdeaService;
    private RecommendedeamService recommendedeamService;

    @Autowired
    public IdeaRecommendationController(RecommendedIdeaService recommendedIdeaService, RecommendedeamService recommendedeamService) {
        this.recommendedIdeaService = recommendedIdeaService;
        this.recommendedeamService = recommendedeamService;
    }


    @GetMapping("skill/{name}")
    public ResponseEntity<Collection<Idea>> getIdeasBySkill(@PathVariable("name") String name) {
        System.out.println(name);
        List<Idea> Ideas = recommendedIdeaService.findBySkill(name);
        System.out.println(Ideas);
        return new ResponseEntity<>(Ideas, HttpStatus.OK);
    }

    @GetMapping("role/{name}")
    public ResponseEntity<Iterable<Idea>> getIdeasByRole(@PathVariable("name") String name) {
        System.out.println(name);
        Iterable<Idea> ideas = recommendedIdeaService.findByRole(name);
        System.out.println("controller");
        return new ResponseEntity<>(ideas, HttpStatus.OK);
    }

    @GetMapping("worked/{name}")
    public ResponseEntity<Iterable<Idea>> getIdeasByPreviousWork(@PathVariable("name") String name, @RequestParam("roleName") String rname) {
        System.out.println(name);
        Iterable<Idea> ideas = recommendedIdeaService.findByWorkedOnIdea(name, rname);
        System.out.println("controller");
        return new ResponseEntity<>(ideas, HttpStatus.OK);
    }

    @GetMapping("applied/{name}")
    public ResponseEntity<Iterable<Idea>> getIdeasByPreviousApplied(@PathVariable("name") String name, @RequestParam("roleName") String rname) {
        System.out.println(name);
        Iterable<Idea> ideas = recommendedIdeaService.findByAppliedOnIdea(name, rname);
        System.out.println("controller");
        return new ResponseEntity<>(ideas, HttpStatus.OK);
    }

    @GetMapping("sp/{name}")
    public ResponseEntity<Collection<ServiceProvider>> getTeam(@PathVariable("name") String name, @RequestParam("experience") String ex, @RequestParam("roleName") String rname) {
        Collection<ServiceProvider> serviceProviders = recommendedeamService.getTeam(name, ex, rname);
        return new ResponseEntity<>(serviceProviders, HttpStatus.OK);
    }

}


