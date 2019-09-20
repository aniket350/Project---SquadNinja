import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IdeaView } from './ideaview.model';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class IdeaviewService {
  constructor(private http: HttpClient) { }
  getIdeaForTitle(title):Observable<any>{
    console.log("in getIdea of reg service "+title);
    return this.http.get<IdeaView>(`http://13.235.10.115:8083/api/v1/idea/${title}`);
  }
  remove(title,emailId):Observable<any>{
    return this.http.put<IdeaView>(`http://13.235.10.115:8083/api/v1/removeSelectedSp?title=${title}&emailId=${emailId}`,(null));
  }
  updateOnAccept(title,emailId,status):Observable<any>{
    return this.http.put<IdeaView>(`http://13.235.10.115:8083/api/v1/acceptssp?title=${title}&emailId=${emailId}&accepted=${status}`,(null));
  }
  updateOnReject(title,emailId,status):Observable<any>{
    return this.http.put<IdeaView>(`http://13.235.10.115:8083/api/v1/acceptssp?title=${title}&emailId=${emailId}&accepted=${status}`,(null));
  }
  updateOnJoin(title,emailId,status):Observable<any>{
    return this.http.put<IdeaView>(`http://13.235.10.115:8083/api/v1/joinedsp,{title=${title}&emailId=${emailId}&accepted=${status}`,(null));
  }

  inviteTeam(idea,invitedSP):Observable<IdeaView>{
    let updated = {
      "title":idea.title,
      "description":"",
      "domain":"",
      "subDomain":"",
      "cost":0,
      "role":[{
      "experience":"",
      "noOfPeople":0,
      "skills":[""],
      "roleName":""}],
      "selectedTeam":[
             {
         "name" : "",
         "mobileNumber" : 0,
         "emailId" :"",
         "skills":[""],
         "chargePerHour": ""
         }
         
         ],
         "invitedTeam":[
                 {
         "name" : invitedSP.name,
         "mobileNumber" : invitedSP.mobileNo,
         "emailId" :invitedSP.emailId,
         "skills":[invitedSP.skills],
         "chargePerHour": invitedSP.chargePerHour
         }
         ],
      "status":"",
      "location":""
      }
      
  
    console.log(updated);
    return this.http.put<IdeaView>(`http://13.235.10.115:8083/api/v1/invitedTeam`,updated);
      
  }
} 