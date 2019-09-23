import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SearchComponenet} from 'src/app/services/cardSearchBar/search-bar.module'
import { Observable } from 'rxjs/internal/Observable';
@Injectable({
    providedIn: 'root'
  })
export class SearchComponentService {
  

    constructor(private http:HttpClient) {
        this.http=http;
       }

       getSearch(title): any {
        return this.http.get<SearchComponenet[]>(`http://13.235.10.115:8090/api/v1/idea/${title}`);

    }
}