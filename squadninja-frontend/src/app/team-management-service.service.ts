import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { from } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TeamManagementServiceService {

  constructor(private http: HttpClient) {
    this.http = http;
  }

  Idea(Title: string, ) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/Json'
      })
    };

    


    //  //  this.title.http.get("http://localhost:8083/idea/${title}", {responseType: 'text'}).subscribe(console.log)
    //   // return this.http.get(`http://localhost:8083/api/v1/idea/${title}`);

    //  return this.http.get(`http://13.235.10.115:8083/api/v1/idea/${title}`); 
    // }
  };


  getRecentEntity(title): Observable < any > {
    return this.http.get(`http://localhost:8083/api/v1/idea/${title}`);

  }

  // getCard(title: string): Observable<any> {
  //   return this.http.get('http://13.235.10.115:8000/api/v1/idea/' + title);
  // }
}







