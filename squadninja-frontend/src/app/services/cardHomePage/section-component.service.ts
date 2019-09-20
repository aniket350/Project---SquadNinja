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

addTeamManagement(modalCardDetails):any {
  return this.http.post('http://13.235.10.115:8083/api/v1/appliedTeam', {
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
       "name" : "krishnaaaa",
       "mobileNumber" : 12345678,
       "emailId" :"mehar@gmail.com",
       "skills":["tester","developer"],
       "chargePerHour": "1000"
       }
       ],
       "invitedTeam":[
               {
       "name" : "manu",
       "mobileNumber" : 12345678,
       "emailId" :"manu@gmail.com",
       "skills":["tester","developer"],
       "chargePerHour": "1000"
       }
       ],
    "status":"yet to complete",
    "location":"bangalore"
    });
}
}
