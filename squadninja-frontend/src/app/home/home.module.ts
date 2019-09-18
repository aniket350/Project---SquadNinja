import { NgModule, NO_ERRORS_SCHEMA  } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import { RouterModule } from '@angular/router';
import {HomeComponent} from './home.component';
import { SectionsModule } from '../sections/sections.module';
import { NavbarComponent } from './navbar/navbar.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { FooterComponent } from './footer/footer.component';
import { MDBBootstrapModule} from 'angular-bootstrap-md';
import { FooterSearchComponent } from './footer-search/footer-search.component';
import {  MatCardModule } from '@angular/material';



@NgModule({
  declarations: [HomeComponent, NavbarComponent, FooterComponent,FooterSearchComponent],
  exports: [HomeComponent],
  imports: [
    CommonModule,
    BrowserModule,
    FormsModule,
    RouterModule,
    SectionsModule,
    NgbModule,
    MDBBootstrapModule,
    
    MatCardModule
   
    
  ]
})
export class HomeModule { }
 