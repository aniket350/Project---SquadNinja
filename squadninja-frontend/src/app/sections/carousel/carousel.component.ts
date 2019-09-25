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
  slideNo:any;
  cardNo:any;
  title:any;
  description:any;

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
  openCard(i,cardIndex)
  {
    this.slideNo=i;
    this.cardNo=cardIndex;
    console.log(this.slideNo);
    console.log(this.cardNo);
    this.title=this.slides[this.slideNo][this.cardNo].title;
    this.description=this.slides[this.slideNo][this.cardNo].description;
    console.log(this.title);
  }

  apply(){
    this.router.navigate(['login']);
  }
}
