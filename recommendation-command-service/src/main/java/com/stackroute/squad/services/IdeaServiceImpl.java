package com.stackroute.squad.services;

import com.stackroute.squad.dto.IdeaDto;
import com.stackroute.squad.domain.Idea;
import com.stackroute.squad.dto.Role;
import com.stackroute.squad.exceptions.IdeaAlreadyExistsException;
import com.stackroute.squad.exceptions.IdeaNotFoundException;
import com.stackroute.squad.repository.IdeaRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */
@Service
public class IdeaServiceImpl implements IdeaService {

  private IdeaRepository ideaRepository;
  private Object Idea;

  /**
   * Constructor based Dependency injection to inject Idea Repository here
   */

  @Autowired
  public IdeaServiceImpl(IdeaRepository ideaRepository) {
    this.ideaRepository = ideaRepository;
  }

  /**
   * Implementation of saveIdea method
   */

  @Override
  public Idea saveIdea(Idea idea) throws IdeaAlreadyExistsException {
    return ideaRepository.save(idea);
  }

  /*It consumes the data of the idea-service*/
  @RabbitListener(queues = "${idea.rabbitmq.queue}")
  public void receiveData(IdeaDto ideaDTO)throws IdeaAlreadyExistsException {
    Idea idea = new Idea();
    idea.setTitle(ideaDTO.getTitle());
    if (ideaRepository.findByTitle(idea.getTitle()) != null) {

      throw new IdeaAlreadyExistsException("idea already exist");
    }
    idea.setDescription(ideaDTO.getDescription());
    idea.setDuration(ideaDTO.getDuration());
    idea.setCost(ideaDTO.getCost());
    idea.setStatus(ideaDTO.getStatus());
    idea.setPostedOn(ideaDTO.getPostedOn());
    ideaRepository.save(idea);

/*it is used for establishing the belongsTo relationship between the idea title and subdomain */
    ideaRepository.setBelongsToRelation(ideaDTO.getTitle(), ideaDTO.getSubDomain().toLowerCase());
/*it is used for setting the requires relationship between the idea title and role*/
    for (int i = 0; i < ideaDTO.getRole().size(); i++) {
      ideaRepository.setRequiresRelation(ideaDTO.getTitle(), ideaDTO.getRole().get(i).getRole().toLowerCase());

    }
    /*It is used for setting the needs relationship between the idea title and skills */
    for (int i = 0; i < ideaDTO.getRole().size(); i++) {
      Role role = new Role();
      role = ideaDTO.getRole().get(i);
      for (int j = 0; j < role.getSkills().size(); j++) {
        ideaRepository.setNeedsRelation(ideaDTO.getTitle(), role.getSkills().get(j).toLowerCase());

      }
    }


  }

  /*It recieves the data from idea service and delete the node and delete the relationship between idea and roles*/
  @RabbitListener(queues = "${ideaDelete.rabbitmq.queue}")
  public void deleteIdea(IdeaDto ideaDto) throws IdeaNotFoundException {
    for (int i = 0; i < ideaDto.getRole().size(); i++) {
      ideaRepository.deleteRequiresRelation(ideaDto.getTitle(), ideaDto.getRole().get(i).getRole());
    }

  }

  /**
   * Implementation of the method to get all the ideas
   */
  @Override
  public List<Idea> getAllIdeas() throws IdeaNotFoundException {
    List<Idea> allIdeas = (List<Idea>) ideaRepository.findAll();
    return allIdeas;
  }


  /**
   * Implementation of the method to update the idea
   */
  @Override
  public Idea updateIdea(Idea idea) throws IdeaNotFoundException {
    Idea updateIdeaById = ideaRepository.save(idea);
    return updateIdeaById;
  }

}

