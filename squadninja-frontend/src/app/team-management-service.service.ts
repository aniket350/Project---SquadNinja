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

  };


  getRecentEntity(title): Observable < any > {
    return this.http.get(`http://localhost:8083/api/v1/idea/${title}`);

  }

}







