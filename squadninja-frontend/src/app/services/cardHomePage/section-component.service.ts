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
  console.log(ideaTitle);
  console.log(ideaTitle.title);
  console.log("naruto5");
  console.log("@@@@@@@@@@@@@@",spApplied);
  
  
  return this.http.put<IdeaView>('http://13.235.10.115:8083/api/v1/appliedTeam',
  {
    "title":ideaTitle.title.trim(),
    "description":"",
    "duration":"",
    "domain":"",
    "subDomain":"",
    "cost":0,
    "role":[{
    "experience":"",
    "noOfPeople":0,
    "skills":["",""],
    "roleName":""}],
    "selectedTeam":[
          {
      "name" : "",
      "mobileNo" : 0,
      "email" :"",
      "role":{
      "role" : "",
      "experience": "",
      "skills": ["",""]
      },
      "chargePerHour": ""
      }
      ],
      "appliedTeam":[
          {
      "name" : spApplied.name,
      "mobileNo" : spApplied.mobileNo,
      "email" :spApplied.email,
      "role":{
      "role" : spApplied.role.role,
      "experience": spApplied.role.experience,
      "skills": spApplied.role.skills
      },
      "chargePerHour": "1000"
      }
      ],
      "invitedTeam":[
              {
      "name" : "",
      "mobileNo" : 0,
      "email" :"",
      "role":{
      "role" : "",
      "experience": "",
      "skills": ["",""]
      },
      "chargePerHour": ""
      }
      ],
    "status":"",
    "location":""
    });
}
}
