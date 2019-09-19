import { Component, OnInit } from '@angular/core';
import { SpprofileserService } from 'src/app/services/spprofileser/spprofileser.service';
import { IdeaviewService } from 'src/app/services/ideaviewser/ideaview.service';

@Component({
  selector: 'app-searchview',
  templateUrl: './searchview.component.html',
  styleUrls: ['./searchview.component.scss']
})
export class SearchviewComponent implements OnInit {

  search:any;
  searchbar: any = [];
  inviteIdea: string;
  idea: any;
  invite:any;
  constructor(private spprofileserService : SpprofileserService,private ideaviewservice:IdeaviewService) { }

  ngOnInit() {
    this.search=localStorage.getItem("search");
    this.spprofileserService.getSearchResults(this.search).subscribe((data) => this.searchbar = data);
  
    console.log(this.searchbar);
  }

  onClickInvite(){

    this.inviteIdea=localStorage.getItem("forTeam");
    // console.log(this.inviteIdea);
    this.idea = JSON.parse(this.inviteIdea);
    console.log(this.idea);
    this.ideaviewservice.inviteTeam(this.idea).subscribe((response)=>this.invite=response);
  }


}
