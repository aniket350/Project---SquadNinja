import { RecommendCards } from './recommendIdeas.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SpProfile } from './spprofile.model';
import { Register } from 'src/app/register/register.model';
import { Observable } from 'rxjs';
import { SearchData } from './search.model';


@Injectable({
  providedIn: 'root'
})
export class  SpprofileserService {
  

  private _url: string = "http://13.235.10.115:8084/api/v1/serviceprovider";
  constructor(private http: HttpClient) { }
  getByEmailIdForServiceProvider(emailId):any{
    return this.http.get<SpProfile>(`http://13.235.10.115:8084/api/v1/serviceprovider/${emailId}`);
  }
  createUser(user: any):Observable<any> {
    return this.http.post<Register>(this._url, user);
  }
  updateTheProfile(profile: any,emailId):Observable<any> {
    let profileUpdated = {
      "email": emailId,
      "name": profile.name,
      "mobileNo": profile.mobileNo,
      "domain": profile.domain,
      "subDomain": profile.subDomain,
      "chargePerHour": profile.chargePerHour,
      "currentLocation": profile.currentLocation,
      "preferredLocation": [profile.prefferedLocation],
      "role":{
      "role" : profile.role,
      "experience": profile.experience,
      "skills": [profile.skills]
    	}
    }
    return this.http.put<any>(`http://13.235.10.115:8084/api/v1/serviceprovider`,profileUpdated);
  }

  getRecommendationIdeas(emailId):Observable<RecommendCards>{
    return this.http.get<RecommendCards>(`http://13.235.10.115:8081/api/v1/role/${emailId}`);
  }

  getSearchResults(roleName):Observable<SearchData>{
    console.log("service called",roleName);
    return this.http.get<SearchData>(`http://13.235.10.115:8084/api/v1/serviceproviders/${roleName}`);
  }

}
