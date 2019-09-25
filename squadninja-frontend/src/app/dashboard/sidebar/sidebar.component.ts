import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}

export const ROUTES: RouteInfo[] = [
  { path: '/ihdashboard', title: 'Dashboard',  icon: 'ni-tv-2 text-white', class: ''},
  { path: 'user-profile', title: 'User Profile',  icon:'ni-single-02 text-yellow', class: '' },
  { path: 'postanidea', title: 'Post An Idea',  icon:'ni-bulb-61 text-info', class: '' },
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  public menuItems: any[];
  public isCollapsed = true;
  constructor(private router:Router) { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    this.router.events.subscribe((event) => {
      this.isCollapsed = true;
   });
  }

}
