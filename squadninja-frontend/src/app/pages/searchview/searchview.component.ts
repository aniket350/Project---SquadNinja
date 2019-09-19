import { Component, OnInit } from '@angular/core';
import { SpprofileserService } from 'src/app/services/spprofileser/spprofileser.service';

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
  constructor(private spprofileserService : SpprofileserService) { }

  ngOnInit() {
    this.search=localStorage.getItem("search");
    this.spprofileserService.getSearchResults(this.search).subscribe((data) => this.searchbar = data);
  
    console.log(this.searchbar);
  }

  onClickInvite(){

    this.inviteIdea=localStorage.getItem("forTeam");
    this.idea = JSON.parse(this.inviteIdea);

  }


}
