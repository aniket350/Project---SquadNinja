import { Component, OnInit } from '@angular/core';
import { SectionComponentService } from 'src/app/services/cardHomePage/section-component.service';
import { SectionComponentSP } from 'src/app/services/cardSPHomePage/section-component-service-service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.scss']
})
export class CarouselComponent implements OnInit {
  private router:Router;
  slides: any = [[]];
  ideaCardsData:any=[];

  constructor(private sectionComponentService : SectionComponentService, private sectionComponentSP: SectionComponentSP){}
  
  ngOnInit() {
    this.sectionComponentService.getIdeas()
    .subscribe(data => {
      this.ideaCardsData = data
      this.slides = this.chunk(this.ideaCardsData, 3);
    });
  }

  chunk(arr, chunkSize) {
    let R = [];
    for (let i = 0, len = arr.length; i < len; i += chunkSize) {
      R.push(arr.slice(i, i + chunkSize));
    }
    return R;
  }
  openCard()
  {
    this.router.navigate(['/login']);
  }
}
