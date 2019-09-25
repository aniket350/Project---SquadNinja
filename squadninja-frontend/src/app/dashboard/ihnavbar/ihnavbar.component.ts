import { Component, OnInit, ElementRef } from '@angular/core';
import { ROUTES } from '../sidebar/sidebar.component';
import { Router } from '@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-ihnavbar',
  templateUrl: './ihnavbar.component.html',
  styleUrls: ['./ihnavbar.component.scss']
})
export class IhnavbarComponent implements OnInit {

  public focus;
  public listTitles: any[];
  public location: Location;
  constructor(location: Location,  private element: ElementRef, private router: Router) {
    this.location = location;
  }
 

  ngOnInit() {
    this.listTitles = ROUTES.filter(listTitle => listTitle);
    console.log(this.listTitles);
  }

}
