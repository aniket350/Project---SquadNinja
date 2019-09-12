package com.stackroute.recommendedqueryservice.repository;

import com.stackroute.recommendedqueryservice.domain.ServiceProvider;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

@Repository
public interface TeamRecommendationService extends Neo4jRepository<ServiceProvider, Long> {
    @Query("match(r:Roles)-[p:played_by]->(sp:ServiceProvider),(i:Idea)-[re:requires]->(r:Roles)-[h:have]->(s:Skills)" +
            " where sp.experience={experience} and i.title={title} and r.roleName={roleName} return sp ")
    Collection<ServiceProvider> getTeam(@Param("title") String name, @Param("experience") String ex, @Param("roleName") String rname);

@Query("match (olr:Roles)-[p:played_by]->(s:ServiceProvider)-[w:worked_on]->(i:Idea)," +
        "(sub:SubDomain)<-[be:belongs_to]-(i:Idea)-[r:requires]->(ro:Roles)-[ha:have]->(n:Skills)<-[hav:have]-(re:Roles)<-[req:requires]-(rec:Idea)" +
        " where not((s:ServiceProvider)-[w:worked_on]->(rec:Idea)) and olr.roleName={roleName}and sub.name={name} and rec.title={title} with s, collect(n) as na" +
        " unwind na as ski match (sub:SubDomain)<-[be:belongs_to]-(i:Idea)-[r:requires]->(ro:Roles)-[ha:have]->(n:Skills)<-[hav:have]-(re:Roles)<-[req:requires]" +
        "-(rec:Idea)-[bel:belongs_to]->(sub1:SubDomain) where re.roleName={roleName}  return distinct s order by s.experience desc")
     Collection<ServiceProvider> getTeamBasedOnWorkedOnIdea(@Param("roleName") String rname,@Param("name") String sub);
}
