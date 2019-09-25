package com.stackroute.squad.repository;

import com.stackroute.squad.domain.Idea;
import com.stackroute.squad.domain.IdeaHamster;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/*@Repository annotation is used to indicate that the class provides the mechanism for storage, retrieval,
 search, update and delete operation on objects.*/
@Repository
public interface IHRepository extends Neo4jRepository<IdeaHamster, Long> {
  public IdeaHamster findById(long id);

  @Query("MATCH (x:IdeaHamster),(y:Idea) WHERE x.email={email} and y.title={title} CREATE (x)-[p:posted_by]->(y) RETURN x")
  public Idea setPlayedByRelation(@Param("email")String email, @Param("title")String title);


}
