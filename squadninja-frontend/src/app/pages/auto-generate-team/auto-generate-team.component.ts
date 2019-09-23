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


    this.title=localStorage.getItem("title");
    this.obj=localStorage.getItem("Role");
    console.log(this.obj);
    this.obj1=JSON.parse(this.obj);
    for(let role of this.obj1){
        this.tabs.push(role.role);
        this.getAnyTeam(role.role);
      }
}
​
getAnyTeam(role:any){
   console.log(role+"getany team method");
  this.autogeneratesp.getByIdeaTitleAndRoleName(this.title,role)
  .subscribe(data =>{
    this.autogeneratesp=data;
    console.log("after getting back from service",this.autogeneratesp);
  }
  ); 
}
}
