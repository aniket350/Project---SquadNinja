import { Component, OnInit ,Input} from '@angular/core';
import { $ } from 'protractor';
import { FormControl } from '@angular/forms';
import {AutogenerateService} from '../../services/autogenServices/autogenerate.service';
import {HttpClient} from '@angular/common/http';
@Component({
  selector: 'app-auto-generate-team',
  templateUrl: './auto-generate-team.component.html',
  styleUrls: ['./auto-generate-team.component.scss']
})
export class AutoGenerateTeamComponent implements OnInit {
  public items: any;
  public postedIdeaDetails: any;
 
​
  title="";
  obj1:any="";
  obj:any="";
 
​
  tabs=[];
  cards = [];
  x: any = [];

  constructor(private autogenerate : AutogenerateService, private http:HttpClient,private autogeneratesp:AutogenerateService) { }
  ngOnInit() {
​
// this.getPostedIdeas(); //Aniket code


    this.title=localStorage.getItem("title");
    this.obj=localStorage.getItem("Role");
    this.obj1=JSON.parse(this.obj);
    for(let role of this.obj1){
        this.tabs.push(role.role);
        this.getAnyTeam(role.role);
      }
}
​
getAnyTeam(role:any){
  // console.log(role);
  this.autogeneratesp.getByIdeaTitleAndRoleName(this.title,role)
  .subscribe(data =>{
    this.autogeneratesp=data;
    console.log("after getting back from service",this.autogeneratesp);
  }
  ); 
}


// //Aniket code for autogeneration team

// getTab(value: any) {
//   console.log(value, 'currentIndex');
//   this.autogenerate.participantsByRole(this.postedIdeaDetails.role[value].role).subscribe((response) => {
//   console.log(response);
//   if (response) {
//      response = response.map(e => {
//       e.acceptStatus = 'Accept';
//       e.rejectStatus = 'Reject';
//       return e
//     })
//     this.items = response;
//     console.log(this.items);
//     }
//     }, (err) => {
//     console.log(err);
//   });
// }

// getPostedIdeas() {
//   this.autogenerate.posetedIdeas().subscribe((response) => {
//     console.log(response);
//     if (response) {
//       this.postedIdeaDetails = response;
//       console.log(response);
//       this.getTab(0);
//       }
//     }, (err) => {
//       console.log(err);
//   });
//  }​

// clickedAccept(item, role) {
//   this.items = this.items.map(e => {
//     if (item.Emailid == e.Emailid) {
//       e.rejectStatus = 'Reject';
//       e.acceptStatus = 'Accepted';
//     }
//     return e;
//  })
// }

// clickedReject(item , role) {
//   console.log(item, role);
//   this.items = this.items.map(e => {
//       if (item.Emailid == e.Emailid) {
//         e.rejectStatus = 'Rejected';
//         e.acceptStatus = 'Accept';
//       }
//       return e;
//   })

// }
}
