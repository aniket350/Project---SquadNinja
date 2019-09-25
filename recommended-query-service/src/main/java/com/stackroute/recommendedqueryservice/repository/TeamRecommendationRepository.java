package com.stackroute.recommendedqueryservice.repository;

import com.stackroute.recommendedqueryservice.domain.Idea;
import com.stackroute.recommendedqueryservice.domain.ServiceProvider;
import com.stackroute.recommendedqueryservice.domain.Skills;
import com.stackroute.recommendedqueryservice.domain.SubDomain;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TeamRecommendationRepository extends Neo4jRepository<SubDomain, Long> {
    @Query("match (ro:Roles)-[p:played_by]->(s:ServiceProvider)-[hs:has_skills]-(sk:Skills),(i:Idea)-[r:requires]->(ro:Roles) where i.title={title} and ro.roleName={roleName} return  s,sk order by s.experience desc")
    List<ServiceProvider> findTeam(@Param("title") String title, @Param("roleName") String roleName);

    @Query("match (ro:Roles)-[p:played_by]->(s:ServiceProvider)-[w:worked_on]->(i:Idea),(i:Idea)-[r:requires]->(ro:Roles)<-[re:requires]-(rec:Idea),(s:ServiceProvider)-[hs:has_skills]-(sk:Skills) where not((s:ServiceProvider)-[w:worked_on]->(rec:Idea)) and rec.title={title} and ro.roleName={roleName} return  s,sk")
    List<ServiceProvider> findTeamBasedOnWorkedOnIdea(@Param("title") String title, @Param("roleName") String roleName);

@Query("match (ro:Roles)-[p:played_by]->(s:ServiceProvider)-[w:applied_for]->(i:Idea),(i:Idea)-[r:requires]->(ro:Roles)<-[re:requires]-(rec:Idea),(s:ServiceProvider)-[hs:has_skills]-(sk:Skills) where not((s:ServiceProvider)-[w:applied_for]->(rec:Idea)) and rec.title={title} and ro.roleName={roleName} return s,sk ")
List<ServiceProvider> findTeamBasedOnAppliedIdea(@Param("roleName") String rname,@Param("title") String title);


}