import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AutogenerateService {

  constructor(private http:HttpClient) { }

  getByIdeaTitleAndRoleName(title,role):any{
    return this.http.get<any>(`http://13.235.10.115:8081/api/v1/applied/${title}?roleName=${role} `);
  }


}
