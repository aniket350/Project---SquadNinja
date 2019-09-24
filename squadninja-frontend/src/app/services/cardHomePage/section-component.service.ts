import { IdeaView } from './../ideaviewser/ideaview.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SectionComponentData } from 'src/app/services/cardHomePage/section-component-model';
import { Observable } from 'rxjs/internal/Observable';
import { SpProfile } from '../spprofileser/spprofile.model';
import { IdeaDetail } from '../spprofileser/IdeaDetail.model';

@Injectable({
  providedIn: 'root'
})
export class SectionComponentService {
  


  private _url : string= 'http://13.235.10.115:8090/api/v1/ideas';

  constructor(private http:HttpClient) {
    this.http=http;
   }

  getIdeas(): Observable<SectionComponentData[]> {
    return this.http.get<SectionComponentData[]>(this._url);
}

myIdeas(emailId):Observable<IdeaDetail[]>{
  return this.http.get<IdeaDetail[]>(`http://13.235.10.115:8090/api/v1/idea/${emailId}`);
}

addTeamManagement(ideaTitle,spApplied):Observable<IdeaView> {
  let name: string = spApplied.name;
  console.log("%%%%%%%%%%%%%%%%",spApplied);
    let mobileNo: string = spApplied.mobileNo;
    let email: string = spApplied.email;
    let role: string = spApplied.role.role;
    let exp: string = spApplied.role.experience;
    let skill: [string] = spApplied.role.skills;
    let charge: string = spApplied.chargePerHour;
  
  
  return this.http.put<IdeaView>('http://13.235.10.115:8083/api/v1/appliedTeam',
  
  {
    "title":"demo1",
    "description":"Online Delivery Pizza Application",
    "duration":"6 months",
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
      "name" : "suri",
      "mobileNo" : 12345678,
      "email" :"suri@gmail.com",
      "role":{
      "role" : "developer",
      "experience": "5 years",
      "skills": ["java","j2ee"]
      },
      "chargePerHour": "1000"
      },
          {
      "name" : "shiv",
      "mobileNo" : 12345678,
      "email" :"shiv@gmail.com",
     "role":{
      "role" : "developer",
      "experience": "5 years",
      "skills": ["java","j2ee"]
      },
      "chargePerHour": "1000"
      },
          {
      "name" : "sruthi",
      "mobileNo" : 12345678,
      "email" :"sruthi@gmail.com",
      "role":{
      "role" : "developer",
      "experience": "5 years",
      "skills": ["java","j2ee"]
      },
      "chargePerHour": "1000"
      },
      {
      "name" : "chintu",
      "mobileNo" : 12345678,
      "email" :"chintu@gmail.com",
        "role":{
      "role" : "developer",
      "experience": "5 years",
      "skills": ["java","j2ee"]
      },
      "chargePerHour": "1000"
      }
      ],
      "appliedTeam":[
          {
      "name" : "sushmita",
      "mobileNo" : 12345678,
      "email" :"sushmita@gmail.com",
      "role":{
      "role" : "developer",
      "experience": "5 years",
      "skills": ["java","j2ee"]
      },
      "chargePerHour": "1000"
      }
      ],
      "invitedTeam":[
              {
      "name" : "don",
      "mobileNo" : 12345678,
      "email" :"don@gmail.com",
      "role":{
      "role" : "developer",
      "experience": "5 years",
      "skills": ["java","j2ee"]
      },
      "chargePerHour": "1000"
      }
      ],
    "status":"yet to complete",
    "location":"bangalore"
    });
}
}
