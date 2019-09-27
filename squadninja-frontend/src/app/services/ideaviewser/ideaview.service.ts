import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { IdeaView } from "./ideaview.model";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class IdeaviewService {
  constructor(private http: HttpClient) {}
  getIdeaForTitle(title): Observable<any> {
    return this.http.get<IdeaView>(
      `http://13.235.10.115:8083/api/v1/idea/${title}`
    );
  }
  remove(title,email):Observable<any>{
    return this.http.put<IdeaView>(`http://13.235.10.115:8083/api/v1/removeSelectedSp?title=${title}&email=${email}`,(null));
  }
  updateOnAccept(title,email,status):Observable<any>{
    return this.http.put<IdeaView>(`http://13.235.10.115:8083/api/v1/acceptssp?title=${title}&email=${email}&accepted=${status}`,(null));
  }
  updateOnReject(title,email,status):Observable<any>{
    return this.http.put<IdeaView>(`http://13.235.10.115:8083/api/v1/acceptssp?title=${title}&email=${email}&accepted=${status}`,(null));
  }
  updateOnJoin(title,email,status):Observable<any>{
    return this.http.put<IdeaView>(`http://13.235.10.115:8083/api/v1/joinedsp?{title=${title}&email=${email}&joined=${status}`,(null));
  }

  updateOnRejectInvite(title,email,status):Observable<any>{
    return this.http.put<IdeaView>(`http://13.235.10.115:8083/api/v1/joinedsp?{title=${title}&email=${email}&joined=${status}`,(null)); 
  }

  inviteTeam(idea:string, invitedSP): Observable<IdeaView> {
    let name: string = invitedSP.name;
    let mobileNo: string = invitedSP.mobileNo;
    let email: string = invitedSP.email;
    let role: string = invitedSP.role.role;
    let exp: string = invitedSP.role.experience;
    let skill: [string] = invitedSP.role.skills;
    let charge: string = invitedSP.chargePerHour;
    console.log("invitedsp", invitedSP.role.skills);
    // console.log("Magento 2 Olegnax bug fix");
    console.log(idea);

    return this.http.put<IdeaView>(
      `http://13.235.10.115:8083/api/v1/invitedTeam`,
      {
        title: idea.trim(),
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
        appliedTeam: [
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
        invitedTeam: [
          {
            name: name,
            mobileNo: mobileNo,
            email: email,
            role: {
              role: role,
              experience: exp,
              skills: skill
            },
            chargePerHour: charge
          }
        ],
        status: "",
        location: ""
      }
    );
  }

  joinedAfterInvite(title,email,joined):Observable<any>{
    return this.http.put<IdeaView>(`http://13.235.10.115:8083/api/v1/joinedsp?title=${title}&email=${email}&joined=${joined}`,(null));
  }
}
