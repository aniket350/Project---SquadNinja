package com.stackroute.controller;

import com.stackroute.domain.ServiceProvider;
import com.stackroute.service.SearchServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/v1")
public class SearchServiceProviderController {

    private SearchServiceProviderService searchServiceProviderService;

    @Autowired
    public SearchServiceProviderController(SearchServiceProviderService searchServiceProviderService) {
        this.searchServiceProviderService = searchServiceProviderService;
    }

    @GetMapping("serviceproviders/{roleName}")
    public ResponseEntity<?> searchResult(@PathVariable String roleName){

        return new ResponseEntity<List<ServiceProvider>>(searchServiceProviderService.searchListOfServiceProvider(roleName), HttpStatus.OK);
    }
}
