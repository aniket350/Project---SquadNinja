package com.stackroute.ideahamsterpostingidea.controller;

import com.stackroute.ideahamsterpostingidea.domain.Idea;
import com.stackroute.ideahamsterpostingidea.exception.IdeaAlreadyExistException;
import com.stackroute.ideahamsterpostingidea.exception.IdeaNotFoundException;
import com.stackroute.ideahamsterpostingidea.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*@RestController annotation in a Spring application to build a Restful controller. */
@CrossOrigin(origins = "*")
@RestController
/*RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods. */
@RequestMapping("api/v1/")
public class IdeaController {

    private IdeaService ideaService;

    public IdeaController() {

    }

    /*@Autowired annotation is used for automatic dependency injection.*/
    @Autowired
    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    /*handle the HTTP POST requests matched with given URI expression.*/
    /*by this PostMapping it will save the idea*/
    @PostMapping("idea")
    public ResponseEntity<?> savedIdea(@RequestBody Idea idea) throws IdeaAlreadyExistException {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        idea.setPostedOn(dateobj);
        Idea savedIdea = ideaService.save(idea);
        return new ResponseEntity<Idea>(savedIdea, HttpStatus.CREATED);
    }

    /* @GetMapping annotated methods handle the HTTP GET requests matched with given URI expression.get the idea by id*/


    /*by this GetMapping it will get the idea which belongs to that title*/
    @GetMapping("idea/{title}")
    public ResponseEntity<?> getIdeaByTitle(@PathVariable String title) throws IdeaNotFoundException,Exception {
        Idea getIdeaByTitle = ideaService.getIdeaByTitle(title);
        return new ResponseEntity<>(getIdeaByTitle, HttpStatus.OK);
    }

    /*by this GetMapping it will get the idea which belongs to that location*/
    @GetMapping("idea/get/{location}")
    public ResponseEntity<?> getIdeaByLocation(@PathVariable String location) throws IdeaNotFoundException,Exception {
        Idea getIdeaBylocation = ideaService.getIdeaByLocation(location);
        return new ResponseEntity<>(getIdeaBylocation, HttpStatus.OK);
    }

    /*by this GetMapping it will get all ideas*/
    @GetMapping("ideas")
    public ResponseEntity<?> getAllIdeas() throws Exception {
        List<Idea> getAllIdeas = ideaService.getAllIdeas();
        return new ResponseEntity<>(getAllIdeas, HttpStatus.OK);
    }


    /*by this DeleteMapping it will delete the idea which belongs to that title*/
    @DeleteMapping("idea/{title}")
    public ResponseEntity<?> deletIdeaByTitle(@PathVariable String title) throws IdeaNotFoundException,Exception {
        Idea deleteIdeaByTitle = ideaService.deleteIdeaByTitle(title);
        return new ResponseEntity<>(deleteIdeaByTitle, HttpStatus.OK);
    }

    /*by this UpdateMapping it will Update the idea*/
    @PutMapping("ideas")
    public ResponseEntity<?> updateIdea(@RequestBody Idea idea) throws IdeaNotFoundException,Exception {
        Idea updateIdea = ideaService.updateIdea(idea);
        return new ResponseEntity<>(updateIdea, HttpStatus.OK);
    }

    /*by this GetMapping it will get All recent ideas*/
    @GetMapping("recentIdeas")
    public ResponseEntity<?> getRecentIdeas() throws Exception {
        List<Idea> recentIdeas = ideaService.getRecentIdeas();
        return new ResponseEntity<>(recentIdeas, HttpStatus.OK);
    }

    /*by this GetMapping it will get All ideas which belongs to that emailId*/
    @GetMapping("postedIdeas/{postedBy}")
    public ResponseEntity<?> getPostedIdeas(@PathVariable String postedBy) throws Exception {
        List<Idea> postedIdeas=ideaService.getPostedByIdeas(postedBy);
        return new ResponseEntity<>(postedIdeas,HttpStatus.OK);
    }

}


