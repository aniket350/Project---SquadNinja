package com.stackroute.teammanagementservice.service;

import com.stackroute.teammanagementservice.domain.Idea;
import com.stackroute.teammanagementservice.dto.*;
import com.stackroute.teammanagementservice.exception.IdeaTitleAlreadyExistException;
import com.stackroute.teammanagementservice.repository.TeamManagementRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@PropertySource("classpath:application.properties")

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service

public class TeamManagementServiceimpl implements TeamManagementService {
    private TeamManagementRepository teamManagementRepository;
    private RabbitTemplate rabbitTemplate;
    /**
     * Constructor based Dependency injection to inject TeamManagementRepository here
     */
    @Autowired
    public TeamManagementServiceimpl(TeamManagementRepository teamManagementRepository, RabbitTemplate rabbitTemplate) {
        this.teamManagementRepository = teamManagementRepository;
       this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${appliedTeam.rabbitmq.exchange}")
    String appliedTeamExchange;

    @Value("${appliedTeam.rabbitmq.routingkey}")
    String appliedTeamRoutingkey;


    @Value("${email.rabbitmq.exchange}")
    String emailTeamExchange;

    @Value("${email.rabbitmq.routingkey}")
    String emailTeamRoutingkey;




    @Value("${invitedIdea.rabbitmq.exchange}")
    String invitedTeamExchange;

    @Value("${invitedIdea.rabbitmq.routingkey}")
    String invitedTeamRoutingkey;



    @Value("${workedOn.rabbitmq.exchange}")
    String workedOnExchange;
    @Value("${workedOn.rabbitmq.routingkey}")
    String workedOnRoutingkey;
    /**
     * Implementation of saveIdea method
     */
    @Override
    public Idea saveIdea(Idea idea) throws IdeaTitleAlreadyExistException {
        /**Throw IdeaTitleAlreadyExistException if title already exists*/
        if (teamManagementRepository.findByTitle(idea.getTitle()) != null) {
            throw new IdeaTitleAlreadyExistException("Title Already Exists");
        }
        Idea savedIdea = teamManagementRepository.save(idea);
        return savedIdea;
    }
    /**
     * Implementation of updateSelectedTeam method
     */
    @Override
    public Idea updateSelectedTeam(Idea idea) {
        Idea retrievedIdea = teamManagementRepository.findByTitle(idea.getTitle());
        List<ServiceProviderDto> serviceProviders = new ArrayList<>();
        serviceProviders = idea.getSelectedTeam();
        for(int i =0;i< serviceProviders.size();i++){
            WorkedTeamDto workedTeamDto = new WorkedTeamDto();
            workedTeamDto.setEmail(serviceProviders.get(i).getEmail());
            workedTeamDto.setIdeaTitle(idea.getTitle());
            rabbitTemplate.convertAndSend(workedOnExchange,workedOnRoutingkey,workedTeamDto);
            System.out.println("sent worked on to recommendation="+workedTeamDto);
        }
        retrievedIdea.setSelectedTeam(serviceProviders);
        return teamManagementRepository.save(retrievedIdea);
    }
    /**
     * Implementation of updateAppliedTeam method
     */
    @Override
    public Idea updateAppliedTeam(Idea idea) {
        Idea retrievedIdea = teamManagementRepository.findByTitle(idea.getTitle());
        List<ServiceProviderDto> serviceProviders;
        if (retrievedIdea.getAppliedTeam() == null) {
            serviceProviders = new ArrayList<>();
        } else {
            serviceProviders = retrievedIdea.getAppliedTeam();

        }
        serviceProviders.add(idea.getAppliedTeam().get(0));
        retrievedIdea.setAppliedTeam(serviceProviders);
        AppliedTeamDto appliedTeamDto = new AppliedTeamDto();
        appliedTeamDto.setEmail(idea.getAppliedTeam().get(0).getEmail());
        appliedTeamDto.setIdeaTitle(idea.getTitle());
        rabbitTemplate.convertAndSend(appliedTeamExchange,appliedTeamRoutingkey,appliedTeamDto);
        return teamManagementRepository.save(retrievedIdea);
    }
    /**
     * Implementation of updateInvitedTeam method
     */
    @Override
    public Idea updateInvitedTeam(Idea idea) {
        Idea retrievedIdea = teamManagementRepository.findByTitle(idea.getTitle());
        List<ServiceProviderDto> serviceProviders;
        if (retrievedIdea.getInvitedTeam() == null) {
            serviceProviders = new ArrayList<>();
        } else {
            serviceProviders = retrievedIdea.getInvitedTeam();
        }
        serviceProviders.add(idea.getInvitedTeam().get(0));
        retrievedIdea.setInvitedTeam(serviceProviders);

        EmailDto emailDto = new EmailDto();
        emailDto.setTo(idea.getInvitedTeam().get(0).getEmail());
        emailDto.setTitle(idea.getTitle());
        emailDto.setBody("You Are Invited For" +emailDto.getTitle());
        rabbitTemplate.convertAndSend(emailTeamExchange,emailTeamRoutingkey,emailDto);

        InvitedDto invitedDto = new InvitedDto();
       // IdeaDto ideaDto = new IdeaDto();
        invitedDto.setTitle(retrievedIdea.getTitle());
        invitedDto.setDescription(retrievedIdea.getDescription());
        invitedDto.setDuration(retrievedIdea.getDuration());
        invitedDto.setDomain(retrievedIdea.getDomain());
        invitedDto.setSubDomain(retrievedIdea.getSubDomain());
        invitedDto.setCost(retrievedIdea.getCost());
        invitedDto.setRole(retrievedIdea.getRole());
        invitedDto.setStatus(retrievedIdea.getStatus());
        invitedDto.setPostedOn(retrievedIdea.getPostedOn());
        invitedDto.setPostedBy(retrievedIdea.getPostedBy());
        invitedDto.setLocation(retrievedIdea.getLocation());
        invitedDto.setInviteeEmailId(idea.getInvitedTeam().get(0).getEmail());
        System.out.println(invitedDto);
        rabbitTemplate.convertAndSend(invitedTeamExchange,invitedTeamRoutingkey,invitedDto);


        System.out.println(emailDto);
        return teamManagementRepository.save(retrievedIdea);

    }

    /**
     * Implementation of acceptedsp/rejectedsp method
     */
    @Override
    public Idea acceptedsp(String title, String email, boolean accepted) {
        Idea retrievedIdea = teamManagementRepository.findByTitle(title);
        List<ServiceProviderDto> appliedList = retrievedIdea.getAppliedTeam();
        List<ServiceProviderDto> selectedList = retrievedIdea.getSelectedTeam();
        Idea updatedIdea = null;
        if (accepted) {
            for (int i = 0; i < appliedList.size(); i++) {
                if (appliedList.get(i).getEmail().equals(email)) {
                    selectedList.add(appliedList.get(i));
                    retrievedIdea.setSelectedTeam(selectedList);
                    appliedList.remove(i);
                     updatedIdea = teamManagementRepository.save(retrievedIdea);
                }
            }
        }
        else {
            for (int i = 0; i < appliedList.size(); i++) {
                if (appliedList.get(i).getEmail().equals(email)) {
                    appliedList.remove(i);
                     updatedIdea = teamManagementRepository.save(retrievedIdea);
                }
            }

        }
        return updatedIdea;
    }
    /**
     * Implementation of joinedsp method
     */
    @Override
    public Idea joinsp(String title,String email,boolean joined){
        Idea retrievedIdea = teamManagementRepository.findByTitle(title);
        List<ServiceProviderDto> invitedList = retrievedIdea.getInvitedTeam();
        List<ServiceProviderDto> selectedList = retrievedIdea.getSelectedTeam();
        Idea updatedIdea = null;
        if(joined){
            for(int i = 0;i < invitedList.size();i++){
                if(invitedList.get(i).getEmail().equals(email)){
                    selectedList.add(invitedList.get(i));
                    retrievedIdea.setSelectedTeam(selectedList);
                    invitedList.remove(i);
                    updatedIdea = teamManagementRepository.save(retrievedIdea);
                }
            }
        }
        else{
            for(int i = 0; i<invitedList.size();i++){
                if(invitedList.get(i).getEmail().equals(email)){
                    invitedList.remove(i);
                    updatedIdea = teamManagementRepository.save(retrievedIdea);
                }
            }
        }
        return updatedIdea;
    }

    /**
     * Implementation of getDetails method
     */
    @Override
    public Idea getDetails(String title) {
      Idea  retrievedIdea = teamManagementRepository.findByTitle(title);
      return retrievedIdea;

    }

    @Override
    public Idea getUpdatedSt(String title,String email) {
        Idea retrieved = teamManagementRepository.findByTitle(title);
        List<ServiceProviderDto> selectedTeam = retrieved.getSelectedTeam();
        Idea updatedSt = null;
        for (int i = 0; i < selectedTeam.size(); i++) {
            if (selectedTeam.get(i).getEmail().equals(email)) {
                selectedTeam.remove(i);
                updatedSt = teamManagementRepository.save(retrieved);
            }

        }
        return updatedSt;
    }
    /**
     * Annotation listens to the queue name called idea and store it in database
     */
    @RabbitListener(queues = "${ideat.rabbitmq.queue}")
    public void receive(IdeaDto ideaDTO) {
        Idea idea = new Idea();
        idea.setTitle(ideaDTO.getTitle());
        idea.setDescription(ideaDTO.getDescription());
        idea.setDuration(ideaDTO.getDuration());
        idea.setDomain(ideaDTO.getDomain());
        idea.setCost(ideaDTO.getCost());
        idea.setRole(ideaDTO.getRole());
        idea.setStatus(ideaDTO.getStatus());
        idea.setLocation(ideaDTO.getLocation());
        teamManagementRepository.save(idea);
    }


}
