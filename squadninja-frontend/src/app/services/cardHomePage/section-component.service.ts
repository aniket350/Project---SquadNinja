import { IdeaView } from './../ideaviewser/ideaview.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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

  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
    })
  };
  // let name: string = spApplied.name;
  // console.log("%%%%%%%%%%%%%%%%",spApplied);
  //   let mobileNo: string = spApplied.mobileNo;
  //   let email: string = spApplied.email;
  //   let role: string = spApplied.role.role;
  //   let exp: string = spApplied.role.experience;
  //   let skill: [string] = spApplied.role.skills;
  //   let charge: string = spApplied.chargePerHour;
  
  let obj={
      title: ideaTitle.title.trim(),
      description: "",
      duration: "",
      domain: "",
      subDomain: "",
      cost: 0,
      role: [
        {
          experience: "",
          noOfPeople: 0,
          skills: ["", ""],
          roleName: ""
        }
      ],
      selectedTeam: [
        {
          name: "",
          mobileNo: 0,
          email: "",
          role: {
            role: "",
            experience: "",
            skills: ["", ""]
          },
          chargePerHour: ""
        }
      ],
      appliedTeam: [ ],
      invitedTeam: [
        {
          name: "",
          mobileNo: "",
          email: "",
          role: {
            role: "",
            experience: "",
            skills: ["",""]
          },
          chargePerHour: ""
        }
      ],
      status: "",
      location: ""
  }
  console.log(spApplied);
  obj.appliedTeam.push(spApplied);
  console.log(obj);
  return this.http.put<IdeaView>('http://13.235.10.115:8083/api/v1/appliedTeam', JSON.stringify(obj),httpOptions);
}
}
