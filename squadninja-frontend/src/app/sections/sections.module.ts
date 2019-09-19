import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule} from '@angular/forms';
import { SectionsComponent } from './sections.component';
import {RouterModule} from '@angular/router';
import { MDBBootstrapModule} from 'angular-bootstrap-md';
import { MatCarouselModule } from '@ngmodule/material-carousel';
import { SlideshowModule } from 'ng-simple-slideshow';
import { CarouselComponent } from './carousel/carousel.component';
import { CarouselModule, WavesModule } from 'angular-bootstrap-md';


@NgModule({
  declarations: [SectionsComponent, CarouselComponent],
  imports: [
    CommonModule,
    NgbModule,
    FormsModule,
    RouterModule,
    MDBBootstrapModule,
    MatCarouselModule,
    SlideshowModule,
    CarouselModule,
    WavesModule
  ],
  exports: [SectionsComponent,CarouselComponent]
})
export class SectionsModule { }
