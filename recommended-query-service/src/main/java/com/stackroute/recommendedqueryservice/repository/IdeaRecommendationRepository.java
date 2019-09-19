package com.stackroute.recommendedqueryservice.repository;

import com.stackroute.recommendedqueryservice.domain.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
/**
 * @Repository marks the specific class as a Data Access Object
 */
@Repository
public interface IdeaRecommendationRepository extends Neo4jRepository<Idea, Long> {

    @Query("MATCH(i:Idea),(sp:ServiceProvider)-[h:has_skills]->(s:Skills) with i,collect(s) as skills" +
            " match (sp)-[h:has_skills]-(s)<-[n:needs]-(i) where sp.email={email} return i")
    /**findBySkill method to get all ideas based on serviceprovider skill*/
    List<Idea> findBySkill(@Param("email") String email);


    @Query("MATCH (sp:ServiceProvider)<-[h:played_by]-(r:Roles)<-[n:requires]-(i) where sp.email={email} return i")
    /**findByRole method to get all ideas based on serviceprovider's role*/
    List<Idea> findByRole(@Param("email") String email);


    @Query("match (ro:Roles)-[pl:played_by]->(s:ServiceProvider)-[w:worked_on]->(i:Idea)," +
            "(i:Idea)-[r:requires]->(ro:Roles)<-[re:requires]-(rec:Idea) " +
            "where not((s:ServiceProvider)-[w:worked_on]->(rec:Idea)) and s.email={email} return distinct rec")
    /**findByWorkedOnIdea method to get all ideas based on serviceprovider's previously worked idea of this application*/
    List<Idea> findByWorkedOnIdea(@Param("email") String email);

    @Query("match (ro:Roles)-[pl:played_by]->(s:ServiceProvider)-[w:applied_for]->(i:Idea)," +
            "(i:Idea)-[r:requires]->(ro:Roles)<-[re:requires]-(rec:Idea) where not" +
            "((s:ServiceProvider)-[w:applied_for]->(rec:Idea)) and s.email={email} return distinct rec")
    List<Idea> findByAppliedOnIdea(@Param("email") String email);

}
