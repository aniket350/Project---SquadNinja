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

  inviteTeam(idea):Observable<any>{
    // console.log(idea);
    return this.http.put<any>(`http://13.235.10.115:8083/api/v1/invitedTeam`,idea);
  }
}
