import { Component, OnInit } from '@angular/core';
import { SectionComponentService } from 'src/app/services/cardHomePage/section-component.service';
import { SectionComponentSP } from 'src/app/services/cardSPHomePage/section-component-service-service';
import { HttpClient } from '@angular/common/http';
import { FormControl } from '@angular/forms';
import { SpprofileserService } from 'src/app/services/spprofileser/spprofileser.service';
import { IdeaviewService } from 'src/app/services/ideaviewser/ideaview.service';

@Component({
  selector: 'app-spdashboardcards',
  templateUrl: './spdashboardcards.component.html',
  styleUrls: ['./spdashboardcards.component.scss']
})
export class SpdashboardcardsComponent implements OnInit {
private_url:string="http://13.235.10.115:8083/api/v1/appliedTeam";

  sections: any = [];
  ideaCardsData: any=[];
  recommendCards: any = [];
  recommendedCarouselCards: any = [[]];
  public emailId :string= "";
  public cardNumber:any;
  public serviceProviderData:any;
  public data:any;
  // private spCardData: any;

//  cards = ['Idea1', 'Idea2','Idea1', 'Idea2','Idea1'];
 sel = new FormControl(0);


  dialog: any;
  rejected: any;
  joined: any;
  


  constructor(private sectionComponentService : SectionComponentService ,private spprofileserService : SpprofileserService,private serviceProviderProfile: SpprofileserService, private ideaviewService : IdeaviewService){}
  ngOnInit() {

    this.emailId=localStorage.getItem("emailId");

    // this.sectionComponentService.getIdeas()
    //   .subscribe(data => {
    //     this.ideaCardsData = data
    //     // console.log(this.ideaCardsData);
    //   });

      this.getTheProfile();
    this.spprofileserService.getRecommendationIdeas(this.emailId)
    .subscribe(data => {
        this.recommendCards = data;
        console.log(this.recommendCards);
        console.log(this.recommendCards[0].title);
        this.recommendedCarouselCards = this.chunk(this.recommendCards, 2);
        
      });

      // this.sectionComponentSP.getSP()
      // .subscribe(data => {
      //   this.spCardData = data
      // });
    // this.sections = this.chunk(this.sections, 3);
    // console.log(this.ideaCardsData);


  }



  chunk(arr, chunkSize) {
    let R = [];
    for (let i = 0, len = arr.length; i < len; i += chunkSize) {
      R.push(arr.slice(i, i + chunkSize));
    }
    return R;
  }

  getTheProfile(){
    this.serviceProviderProfile.getByEmailIdForServiceProvider(this.emailId)
    .subscribe((data)=> {
      console.log("data fetched..", data);
      this.serviceProviderData=data;
      console.log("after getting back from service",this.serviceProviderData);
    });
  }

  apply(cardNumber){

   let spApplied= {
          name: "",
          mobileNo: "",
          email: "",
          role: {
            role: "",
            experience: "",
            skills: []
          },
          chargePerHour: ""
        }

    console.log(cardNumber);
    console.log(this.recommendCards[cardNumber]);
    console.log(this.serviceProviderData);
    spApplied.name=this.serviceProviderData.name;
    spApplied.mobileNo=this.serviceProviderData.mobileNo;
    spApplied.email=this.serviceProviderData.email;
    spApplied.role.skills=this.serviceProviderData.role.skills;
    spApplied.chargePerHour=this.serviceProviderData.chargePerHour;


    this.sectionComponentService.addTeamManagement(this.recommendCards[cardNumber],spApplied).subscribe(res => {
         this.data=res;
         console.log("#################",this.data);
    }, err => {
      console.log(err)
    });

    
  }


  clicked(index:any){
    console.log(this.recommendCards[index]);
    console.log(index);
    this.cardNumber=index;
    // this.saveCardDetails(this.ideaCardsData[index]);
  }

  accept(cardnumber){
    console.log(cardnumber);
    console.log(this.serviceProviderData.invitedIdeas[cardnumber].title);
    let title = this.serviceProviderData.invitedIdeas[cardnumber].title;

    console.log(String(title));
    let joined: boolean =true;
    this.ideaviewService.joinedAfterInvite(String(title),this.emailId,joined)
    .subscribe((data)=> {
      this.joined=data;
      console.log("after getting back from service",this.joined);
    });
    this.serviceProviderData.invitedIdeas.splice(cardnumber,1);
  }

  reject(cardnumber){
    console.log(cardnumber);
    this.serviceProviderData.invitedIdeas.splice(cardnumber,1);
    console.log(this.serviceProviderData.invitedIdeas[cardnumber].title);
    let title = this.serviceProviderData.invitedIdeas[cardnumber].title;
    console.log(String(title));
    let joined: boolean =false; 
    this.ideaviewService.joinedAfterInvite(title,this.emailId,joined)
    .subscribe((data)=> {
      this.rejected=data;
      console.log("after getting back from service",this.rejected);
    });
  }
}
