import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart, NavigationEnd } from '@angular/router';
import {Location,PopStateEvent} from '@angular/common';

//fetching from search bar
import {SearchComponentService} from 'src/app/services/cardSearchBar/search-bar.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
 public isCollapsed=true;
 private lastPoppedUrl:string;
 private yScrollStack:number[]=[];
 

 //for search bar
 private searchbar:any;
 public title:any = '';

  constructor(public location:Location,private router:Router,
    private searchComponentService : SearchComponentService) { }

  ngOnInit() 
{

  //search bar subscribe start
  

//searchbar subscribe end
    this.router.events.subscribe((event)=>{
      this.isCollapsed = true;
      if (event instanceof NavigationStart) {
         if (event.url != this.lastPoppedUrl)
             this.yScrollStack.push(window.scrollY);
     } else if (event instanceof NavigationEnd) {
         if (event.url == this.lastPoppedUrl) {
             this.lastPoppedUrl = undefined;
             window.scrollTo(0, this.yScrollStack.pop());
         } else
             window.scrollTo(0, 0);
     }
    });
    this.location.subscribe((ev:PopStateEvent) => {
      this.lastPoppedUrl = ev.url;
  });
  }

  isHome() {
    var titlee = this.location.prepareExternalUrl(this.location.path());

    if( titlee === '#/home' ) {
        return true;
    }
    else {
        return false;
    }
}
isDocumentation() {
    var titlee = this.location.prepareExternalUrl(this.location.path());
    if( titlee === '#/documentation' ) {
        return true;
    }
    else {
        return false;
    }
}
searchLogs(searchTerm: string)
{
  // console.log(1234);
  // console.log(this.title);
  this.searchComponentService.getSearch(this.title).subscribe((val) => this.searchbar = val);
  console.log(this.searchbar);
  this.router.navigate(['footersearch'])
   }

  // {
  //     if (!!searchTerm){
  //         this.searchComponentService.getSearch(searchTerm).subscribe((val) => this.searchbar = val);
  //     } else{
  //         console.log("Empty string given");
  //     }

      // this.router.navigate(['footersearch'])




    //   console.log(searchTerm);
    //   console.log(this.title);
      
    //   window.alert()
    // console.log("sss");
    //     this.searchComponentService.getSearch(this.title)
    //       .subscribe(data => {
    //           console.log("kkkk")
    //         this.searchbar = data
    //         console.log(this.searchComponentService);
            
    //       });
        }

    

