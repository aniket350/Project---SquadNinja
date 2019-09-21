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
  remove(title,email):Observable<any>{
    return this.http.put<IdeaView>(`http://13.235.10.115:8083/api/v1/removeSelectedSp?title=${title}&emailId=${email}`,(null));
  }
  updateOnAccept(title,email,status):Observable<any>{
    return this.http.put<IdeaView>(`http://13.235.10.115:8083/api/v1/acceptssp?title=${title}&emailId=${email}&accepted=${status}`,(null));
  }
  updateOnReject(title,email,status):Observable<any>{
    return this.http.put<IdeaView>(`http://13.235.10.115:8083/api/v1/acceptssp?title=${title}&emailId=${email}&accepted=${status}`,(null));
  }
  updateOnJoin(title,email,status):Observable<any>{
    return this.http.put<IdeaView>(`http://13.235.10.115:8083/api/v1/joinedsp,{title=${title}&emailId=${email}&accepted=${status}`,(null));
  }

  inviteTeam(idea,invitedSP):Observable<IdeaView>{
    console.log(idea.title);
    console.log("invitedsp",invitedSP.role.skills);
    let updated = {
      "title":idea.title,
      "description":"",
      "domain":"java",
      "subDomain":"js",
      "cost":0,
      "role":[{
      "experience":"",
      "noOfPeople":0,
      "skills":[],
      "roleName":""}],
      "selectedTeam":[
             {
         "name" : "",
         "mobileNumber" : 0,
         "emailId" :"",
         "skills":[],
         "chargePerHour": ""
         },
             {
         "name" : "",
         "mobileNumber" :0,
         "emailId" :"",
         "skills":[],
         "chargePerHour": ""
         },
             {
         "name" : "",
         "mobileNumber" : 0,
         "emailId" :"",
         "skills":[],
         "chargePerHour": ""
         }
         ],
         "appliedTeam":[
         {
         "name" : "",
         "mobileNumber" : 0,
         "emailId" :"",
         "skills":[],
         "chargePerHour": ""
         }
         ],
         "invitedTeam":[
                 {
         "name" : invitedSP.name,
         "mobileNo" : invitedSP.mobileNo,
         "email" : invitedSP.email,
         "skills":invitedSP.role.skills,
         "chargePerHour": invitedSP.chargePerHour
         }
         ],
      "status":"",
      "location":""
      }
      
  
    console.log("after adding all the data",updated);
    return this.http.put<IdeaView>(`http://13.235.10.115:8083/api/v1/invitedTeam`,updated);
      
  }
} 