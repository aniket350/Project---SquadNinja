import { Component, OnInit } from '@angular/core';
import { SectionComponentService } from 'src/app/services/cardHomePage/section-component.service';
import { SectionComponentSP } from 'src/app/services/cardSPHomePage/section-component-service-service';

@Component({
  selector: 'app-spdashboardcards',
  templateUrl: './spdashboardcards.component.html',
  styleUrls: ['./spdashboardcards.component.scss']
})
export class SpdashboardcardsComponent implements OnInit {

  sections: any = [];
  private ideaCardsData: any;
  private spCardData: any;
  cards:any=['one','two'];
  public emailId='';
  constructor(private sectionComponentService : SectionComponentService, private sectionComponentSP: SectionComponentSP){}
  ngOnInit() {
    this.emailId=localStorage.getItem("emailId");
    this.sectionComponentService.myIdeas(this.emailId)
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

}
