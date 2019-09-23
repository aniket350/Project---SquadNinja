package com.stackroute.service;

import com.stackroute.domain.Idea;
import com.stackroute.domain.ServiceProvider;
import com.stackroute.dto.InvitedIdeaDto;
import com.stackroute.dto.ServiceProviderDto;
import com.stackroute.exception.UserAlreadyFoundException;

import java.util.List;

public interface ServiceProviderService {


    /**
     * AbstractMethod to save  ServiceProvider
     */
    ServiceProvider saveServiceProvider(ServiceProviderDto provider) throws UserAlreadyFoundException;

    /**
     * AbstractMethod to get  ServiceProvider
     */
    ServiceProvider getTheProfile(String email);

    /**
     * AbstractMethod to update  ServiceProvider
     */
    ServiceProvider updateTheProfile(ServiceProvider provider);



}
