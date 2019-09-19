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
  // sel = new FormControl(0);
  // public serviceproviders = [];
​
// tslint:disable-next-line:max-line-length
  constructor(private http:HttpClient,private autogeneratesp:AutogenerateService) { }
  ngOnInit() {
​
  
​
   // this.getPostedIdeas().then(() => this.getTab(0));
    this.title=localStorage.getItem("title");
    // console.log(this.title);
    this.obj=localStorage.getItem("Role");
    // console.log(this.obj);
    this.obj1=JSON.parse(this.obj);
    // console.log(this.obj1);
    for(let role of this.obj1){
      // console.log(role);
        this.tabs.push(role.role);
        this.getAnyTeam(role.role);
      }
 
  // this.getAnyTeam();
}
​
getAnyTeam(role:any){
  // console.log(role);
  this.autogeneratesp.getByIdeaTitleAndRoleName(this.title,role)
  .subscribe(data =>{
    // console.log("data from posting an idea "+ this.title,role);
    this.autogeneratesp=data;
    console.log("after getting back from service",this.autogeneratesp);
  }
  ); 
}
​
​
 clickedAccept(item, role) {
    item.statusA = "Accepted";
    let designerpath: string = 'http://localhost:3000/' + role + '/' + item.id;
    console.log(designerpath);
    let value = 'Accepted';
    let statusR = 'Reject';
​
    this.http.patch(designerpath,{'statusA' : value, 'statusR' : statusR}).subscribe();
​
  }
​
  clickedReject(id) {
    let despath: string = 'http://localhost:3000/designers/' + id;
    let value = 'Accept';
    let statusR = 'Rejected';
    this.http.patch(despath, {'statusR': statusR,'statusA': value}).subscribe();
​
  }
​

}
