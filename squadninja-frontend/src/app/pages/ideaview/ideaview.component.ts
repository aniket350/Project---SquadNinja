import { Component, OnInit } from '@angular/core';
import { IdeaviewService } from 'src/app/services/ideaviewser/ideaview.service';
import { Observable, interval, Subscription } from 'rxjs';


@Component({
  selector: 'app-ideaview',
  templateUrl: './ideaview.component.html',
  styleUrls: ['./ideaview.component.scss']
})
export class IdeaviewComponent implements OnInit {
  appliedTeam : any;
  selectedTeam : any;
  invitedTeam : any;
  title : string;
  description : string;
  public status:boolean = false;

  constructor(private ideaviewService : IdeaviewService) { }
  public titlehc = 'Application';

  ngOnInit() {
    this.getIdeaDetails();
  }
  getIdeaDetails() {
    this.ideaviewService.getIdeaForTitle(this.titlehc).subscribe((data) => {
     console.log("data fetched..",data.appliedTeam);
     this.title=data.title;
     this.description=data.description;
     this.appliedTeam=data.appliedTeam;
     this.selectedTeam=data.selectedTeam;
     this.invitedTeam=data.invitedTeam;
    
    });
   
  }
  
  removeCard(emailId) {
    this.ideaviewService.remove(this.title,emailId).subscribe((data) =>{
    });
    window.location.reload();
  }
  save(emailId){
    console.log(emailId);
    this.status=true;
    console.log(status);
    this.ideaviewService.updateOnAccept(this.title,emailId,this.status).subscribe((data) =>{
    });
    window.location.reload();
  }
  reject(emailId){
     this.status=false;
     this.ideaviewService.updateOnReject(this.title,emailId,this.status).subscribe((data) =>{
  });
  window.location.reload();
}

  join(emailId) {
    this.status=true;
    this.ideaviewService.updateOnJoin(this.title,emailId,this.status).subscribe((data) =>{

    });
  }
}
