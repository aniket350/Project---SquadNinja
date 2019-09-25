import { Component, OnInit, ViewChild } from '@angular/core';
import { SectionComponentService } from 'src/app/services/cardHomePage/section-component.service';
import {SectionComponentSP} from 'src/app/services/cardSPHomePage/section-component-service-service'
import { Router } from '@angular/router';
import { MatCarousel, MatCarouselComponent } from '@ngmodule/material-carousel';

@Component({
  selector: 'app-sections',
  templateUrl: './sections.component.html',
  styleUrls: ['./sections.component.scss']
})
export class SectionsComponent implements OnInit 
{
  focus;
  focus1;
  private router:Router;
 


  sections: any = [];
  private ideaCardsData: any;
  private spCardData: any;
  private images: string[];


  slides: any = [[]];

  constructor(private sectionComponentService : SectionComponentService, private sectionComponentSP: SectionComponentSP){}
  ngOnInit() {
    this.sectionComponentService.getIdeas()
      .subscribe(data => {
        this.ideaCardsData = data
      });
  }
  openCard()
  {
    this.router.navigate(['/']);
  }

}
