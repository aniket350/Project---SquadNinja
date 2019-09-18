import { SearchviewComponent } from './../../pages/searchview/searchview.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IhdashboardcardsComponent } from 'src/app/pages/ihdashboardcards/ihdashboardcards.component';
import { IhprofileComponent } from 'src/app/pages/ihprofile/ihprofile.component';
import { PostideaComponent } from 'src/app/pages/postidea/postidea.component';
import { AutoGenerateTeamComponent } from 'src/app/pages/auto-generate-team/auto-generate-team.component';
import { IdeaviewComponent } from 'src/app/pages/ideaview/ideaview.component';

export const IhDashboardRoutes: Routes = [
  {path: '', component: IhdashboardcardsComponent},
  {path: 'ihdashboardcards', component: IhdashboardcardsComponent},
  {path: 'user-profile', component: IhprofileComponent},
  {path: 'postanidea', component: PostideaComponent},
  {path: 'postanidea/autogenerateTeam',component: AutoGenerateTeamComponent},
  {path: 'ideaview',component: IdeaviewComponent},
  {path: 'ideaview/searchsp',component: SearchviewComponent}
];



