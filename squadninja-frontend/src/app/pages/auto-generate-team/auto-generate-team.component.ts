import { Component, OnInit ,Input} from '@angular/core';
import { $, element } from 'protractor';
import { FormControl } from '@angular/forms';
import {AutogenerateService} from '../../services/autogenServices/autogenerate.service';
import {HttpClient} from '@angular/common/http';
import { map } from 'rxjs/operators';
@Component({
  selector: 'app-auto-generate-team',
  templateUrl: './auto-generate-team.component.html',
  styleUrls: ['./auto-generate-team.component.scss']
})
export class AutoGenerateTeamComponent implements OnInit {
  public items: any;
  public postedIdeaDetails: any;
 
  title:any="";
  obj1:any="";
  obj:any="";
  disabled:boolean=true;
  cards = [];
  example=['one','two'];
  roleCards=[];
  tabs=[];
  response:any;
  x: any = [];
  accept:any=[];
  sendAccept={
    role:{
      role:"",
      experience:"",
      skills:[],
    }
}
  toSendData = new Map();
  constructor( private http:HttpClient,private autogeneratesp:AutogenerateService) { }
  ngOnInit() {
     let  disp=[];
// this.getPostedIdeas(); 
    this.title=localStorage.getItem("title");
    this.obj=localStorage.getItem("Role");
    this.obj1=JSON.parse(this.obj);
    for(let role of this.obj1){
        this.tabs.push(role.role);
        this.getAnyTeam(role.role);
        disp=this.cards;
        console.log(this.cards);
      }
     
}
sendAutogenTeam(){
  console.log(this.toSendData);
  for(let [key,value] of this.toSendData){
    this.accept.push(value);
  }
  this.toSendData = null;
  console.log(this.accept);
  console.log(this.accept.length);
  let i =0;
  ['currentLocation' ,'domain','id' ,'idea','preferredLocation', 'ideaDto' , 'previousProject', 'skillList','subDomain'].forEach(element => 
  this.accept.forEach(e => {
      delete e[element];
    })
  );
  this.accept = this.accept.map(element => {
      this.sendAccept.role.role = element.role;
      this.sendAccept.role.experience = element.experience;
      this.sendAccept.role.skills = element.skills; 
      element.role = this.sendAccept.role; 
      ['experience','skills'].forEach( e => delete element[e]);
      return element;
   });
  console.log(this.accept);
  this.autogeneratesp.sendAutogeneratedTeam(this.accept,this.title).subscribe((data)=>{
  this.response=data;
  });
}
addCards(toAdd){
  console.log(toAdd);
  this.cards.push(toAdd);
  console.log(this.cards);
}
getAnyTeam(role: any) {
  // console.log(role);
  this.autogeneratesp.getByIdeaTitleAndRoleName(this.title, role)
  .subscribe(data => {
    this.cards = data;
    if (data) {
           data = data.map(e => {
            e.acceptStatus = 'Accept';
            e.rejectStatus = 'Reject';
            return e;
          });
          this.cards = data;
           this.roleCards.push(this.cards);
    // this.addCards(this.cards);
    console.log('after getting back from service', this.roleCards);
          }
  }
  );
  // console.log(this.cards);
 }
 clickAccept(card,index,i) {
  
  this.disabled = false;
  console.log(this.roleCards[index][i]);
  this.toSendData.set(2 * index + 1 * i, this.roleCards[index][i]);
  console.log(this.toSendData);
  if (this.roleCards) {
  this.roleCards = this.roleCards.map(e => {
  e = e.map(j => {
  if (card.email == j.email) {
  j.rejectStatus = 'Reject';
  j.acceptStatus = 'Accepted';
  }
  return j;
 });
  return e;
    });
    }
 }
 clickReject(card, index, i) {
 
  this.toSendData.delete(2 * index + 1 * i);
  console.log(this.toSendData);
  console.log(index);
  console.log(i);
  console.log('Rejected');
  if (this.roleCards) {
  this.roleCards = this.roleCards.map(e => {
  e = e.map(j => {
  if (card.email == j.email) {
  j.rejectStatus = 'Rejected';
  j.acceptStatus = 'Accept';
  }
  return j;
  });
  return e;
  });
  }
 }
}