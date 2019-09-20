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
      "title":"Magento 2 Olegnax bug fix",
      "description":"Online Delivery Pizza Application",
      "domain":"java",
      "subDomain":"js",
      "cost":10000,
      "role":[{
      "experience":"5 years",
      "noOfPeople":5,
      "skills":["php","java"],
      "roleName":"Developer"}],
      "selectedTeam":[
             {
         "name" : "adithya",
         "mobileNumber" : 12345678,
         "emailId" :"adithya@gmail.com",
         "skills":["tester","developer"],
         "chargePerHour": "1000"
         },
             {
         "name" : "mansi",
         "mobileNumber" : 12345678,
         "emailId" :"mansi@gmail.com",
         "skills":["tester","developer"],
         "chargePerHour": "1000"
         },
             {
         "name" : "ramya",
         "mobileNumber" : 12345678,
         "emailId" :"ramya@gmail.com",
         "skills":["tester","developer"],
         "chargePerHour": "1000"
         }
         ],
         "appliedTeam":[
         {
         "name" : "mehar",
         "mobileNumber" : 12345678,
         "emailId" :"mehar@gmail.com",
         "skills":["tester","developer"],
         "chargePerHour": "1000"
         }
         ],
         "invitedTeam":[
                 {
         "name" : "ramya",
         "mobileNumber" : 12345678,
         "emailId" :"manu@gmail.com",
         "skills":["tester","developer"],
         "chargePerHour": "1000"
         }
         ],
      "status":"yet to complete",
      "location":"bangalore"
      }
      
  
    console.log(updated);
    return this.http.put<IdeaView>(`http://13.235.10.115:8083/api/v1/invitedTeam`,updated);
      
  }
} 