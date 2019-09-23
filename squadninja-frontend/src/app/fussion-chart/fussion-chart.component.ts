import { Component ,OnInit, ɵɵinjectAttribute } from '@angular/core';
import { TeamManagementServiceService } from '../team-management-service.service';
import { from } from 'rxjs';
import { log } from 'util';

@Component({
  selector: 'app-fussion-chart',
  templateUrl: './fussion-chart.component.html',
  styleUrls: ['./fussion-chart.component.scss']
})
export class FussionChartComponent {
  dataSource: Object;
  label:any;
  value:any;
  constructor(
    private teamManagementService: TeamManagementServiceService) {
   }

  ngOnInit() {
    
    this.teamManagementService.getRecentEntity('Application').subscribe(data => {
      const invitedTeam = data.invitedTeam.length;
      const selectedTeam = data.selectedTeam.length;
      const appliedTeam = data.appliedTeam.length;

      this.dataSource = {
        chart: {
          caption: " IdeaHamster [2018-19]",
          subCaption: "In Idea = One Million barrels",
          xAxisName: "MyIdea",
          yAxisName: "Work on Idea",
          numberSuffix: "k",
          theme: "fusion"
        },
        
        // Chart Data
        data: [
          {
            label: "InvitedTeam",
            value: invitedTeam
          },
          {
            label: "AppliedTeam",
            value: appliedTeam
          },
          {
            label: "SelectedTeam",
            value: selectedTeam

          }, 

        ]

      }
      console.log(this.dataSource)
    });
  }
  
  
}


