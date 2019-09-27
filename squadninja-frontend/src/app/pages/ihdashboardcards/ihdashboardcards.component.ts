import { IhprofileserService } from 'src/app/services/ihprofileser/ihprofileser.service';
import { IhProfile } from './../../services/ihprofileser/ihprofile.model';
import { Component, OnInit } from '@angular/core';
import {$} from 'protractor';
import { protractor } from 'protractor/built/ptor';
import { FormControl } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SectionComponentService } from 'src/app/services/cardHomePage/section-component.service';
import { SectionComponentSP } from 'src/app/services/cardSPHomePage/section-component-service-service';
import { SpprofileserService } from 'src/app/services/spprofileser/spprofileser.service';

@Component({
  selector: 'app-ihdashboardcards',
  templateUrl: './ihdashboardcards.component.html',
  styleUrls: ['./ihdashboardcards.component.scss']
})
export class IhdashboardcardsComponent implements OnInit {

  sections: any = ['title'];
  ideaCardsData: any = [];
  ideaCardsDatas: any = [];
  ideaCarouselCards: any = [[]];
  imageArray: any = [];
  updated: any;

  cards:any=['one','two'];

  constructor(private ihprofileserService : IhprofileserService,private http:HttpClient){}

  public emailId = '';
  ngOnInit() {
    this.getTheProfile();
    this.getPostedIdeas();
    this.getJSON().subscribe(data =>{this.imageArray = data;console.log(this.imageArray)}, error => console.log(error));
    // this.sectionComponentService.getIdeas()
    //   .subscribe(data => {
    //     this.ideaCardsData = data
    //     console.log(this.ideaCardsData);
    //   });

    this.sections = this.chunk(this.sections, 3);
  }


  chunk(arr, chunkSize) {
    let R = [];
    for (let i = 0, len = arr.length; i < len; i += chunkSize) {
      R.push(arr.slice(i, i + chunkSize));
    }
    return R;
  }
  public getJSON(): Observable<any> {
     return this.http.get("../assets/data/subDomainImage.json");
 }

  getTheProfile(){
    this.emailId=localStorage.getItem("emailId");
    this.ihprofileserService.getByEmailIdForIdeaHamster(this.emailId)
    .subscribe((data)=> {
      console.log("data fetched..", data);
      this.ideaCardsData=data;
      console.log("after getting back from service",this.ideaCardsData);

    });

  }

  getPostedIdeas(){
    this.emailId=localStorage.getItem("emailId");
    console.log(this.emailId);
    this.ihprofileserService.getIdea(this.emailId).subscribe((data)=>{
      console.log(data);
      this.ideaCardsDatas=data;
      // localStorage.setItem("forTeam", JSON.stringify(this.ideaCardsDatas));
      // console.log(this.ideaCardsDatas);
     // this.ideaCarouselCards = this.chunk(this.ideaCardsDatas, 5);

    });
  }

  getIdeaDetails(index:any){
    console.log(index);
    localStorage.setItem("forTeam", JSON.stringify(this.ideaCardsDatas[index]));
    console.log(this.ideaCardsDatas[index]);
  }
}
