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

  chunk(arr, chunkSize) {
    let R = [];
    for (let i = 0, len = arr.length; i < len; i += chunkSize) {
      R.push(arr.slice(i, i + chunkSize));
    }
    return R;
  }
  slides: any = [[]];

  constructor(private sectionComponentService : SectionComponentService, private sectionComponentSP: SectionComponentSP){}
  ngOnInit() {
    this.sectionComponentService.getIdeas()
      .subscribe(data => {
        this.ideaCardsData = data
    
        
      });
      this.slides = this.chunk(this.ideaCardsData,5);
     
  }
  openCard()
  {
    this.router.navigate(['/login']);
  }

}
