import { Component, OnInit } from '@angular/core';
import { SectionComponentService } from 'src/app/services/cardHomePage/section-component.service';
import { SectionComponentSP } from 'src/app/services/cardSPHomePage/section-component-service-service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-spdashboardcards',
  templateUrl: './spdashboardcards.component.html',
  styleUrls: ['./spdashboardcards.component.scss']
})
export class SpdashboardcardsComponent implements OnInit {
private_url:string="http://13.235.10.115:8083/api/v1/appliedTeam";

  sections: any = [];
  private ideaCardsData: any;
  private spCardData: any;
  
  modalCardDetails = {
    title: '',
    description: '',
    role: '',
    domain: '',
    subdomain: ''
                
  }

  chunk(arr, chunkSize) {
    let R = [];
    for (let i = 0, len = arr.length; i < len; i += chunkSize) {
      R.push(arr.slice(i, i + chunkSize));
    }
    return R;
  }
  constructor(private sectionComponentService : SectionComponentService, private sectionComponentSP: SectionComponentSP, private http:HttpClient){}
  ngOnInit() {
    this.sectionComponentService.getIdeas()
      .subscribe(data => {
        this.ideaCardsData = data
        console.log(this.ideaCardsData);
      });

      this.sectionComponentSP.getSP()
      .subscribe(data => {
        this.spCardData = data
      });
    this.sections = this.chunk(this.sections, 3);
  }

  saveCardDetails(x: any) {
    console.log(x);
    this.modalCardDetails.title = x.title;
    this.modalCardDetails.description = x.description;
    this.modalCardDetails.role = x.role;
    this.modalCardDetails.domain = x.domain;
    this.modalCardDetails.subdomain = x.subDomain;
  
  }

  applyData() {
    this.sectionComponentService.addTeamManagement(this.modalCardDetails).subscribe(res => {

    }, err => {
      console.log(err)
    })
  }
}
