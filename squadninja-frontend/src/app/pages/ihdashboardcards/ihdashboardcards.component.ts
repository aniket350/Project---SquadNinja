import { Component, OnInit } from '@angular/core';
import {$} from 'protractor';
import { protractor } from 'protractor/built/ptor';
import { FormControl } from '@angular/forms';
import { IhdashboardserService } from 'src/app/services/ihdashboardser/ihdashboardser.service';
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
  private ideaCardsData: any;

  serviceProviderData: SpprofileserService;
  updated: any;

  cards:any=['one','two'];
  constructor(private sectionComponentService : SectionComponentService, private sectionComponentSP: SectionComponentSP,private serviceProviderProfile: SpprofileserService){}

  public emailId = '';
  ngOnInit() {
    this.getTheProfile();

    this.sectionComponentService.getIdeas()
      .subscribe(data => {
        this.ideaCardsData = data
        console.log(this.ideaCardsData);
      });
      
    this.sections = this.chunk(this.sections, 3);
  }


  chunk(arr, chunkSize) {
    let R = [];
    for (let i = 0, len = arr.length; i < len; i += chunkSize) {
      R.push(arr.slice(i, i + chunkSize));
    }
    return R;
  }

  getTheProfile(){
    this.emailId=localStorage.getItem("emailId");
    this.serviceProviderProfile.getByEmailIdForServiceProvider(this.emailId)
    .subscribe((data)=> {
      console.log("data fetched..", data);
      this.serviceProviderData=data;
      console.log("after getting back from service",this.serviceProviderData);
    });
  }


}
 