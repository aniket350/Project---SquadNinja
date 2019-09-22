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
  public titlehc = '';
  public forTeam:any;
  public obj:any;

  ngOnInit() {
    this.forTeam=localStorage.getItem("forTeam");
    console.log("Forteam" ,this.forTeam);
    this.obj=JSON.parse(this.forTeam);
    console.log("titleeeeeeeeeeee",this.obj.title);
    localStorage.setItem("title",this.obj.title);
    this.getIdeaDetails();
  }
  getIdeaDetails() {
    this.titlehc=this.obj.title;
    console.log(this.titlehc);
    this.ideaviewService.getIdeaForTitle(this.titlehc).subscribe((data) => {
     console.log("data fetched..",data.selectedTeam);



     this.title=data.title;
     this.description=data.description;
     this.appliedTeam=data.appliedTeam;
     this.selectedTeam=data.selectedTeam;
     this.invitedTeam=data.invitedTeam;
     
    });
   
  }

  
  removeCard(email) {
    this.ideaviewService.remove(this.title,email).subscribe((data) =>{
    });
    // window.location.reload();
  }
  save(email){
    console.log(email);
    this.status=true;
    console.log(status);
    this.ideaviewService.updateOnAccept(this.title,email,this.status).subscribe((data) =>{
    });
    // window.location.reload();
  }
  reject(email){
     this.status=false;
     this.ideaviewService.updateOnReject(this.title,email,this.status).subscribe((data) =>{
  });
  // window.location.reload();
}

  join(email) {
    this.status=true;
    this.ideaviewService.updateOnJoin(this.title,email,this.status).subscribe((data) =>{

    });
  }

  onSearch(searchValue:string){

    localStorage.setItem("search",searchValue);
    console.log(searchValue);

  }
}
