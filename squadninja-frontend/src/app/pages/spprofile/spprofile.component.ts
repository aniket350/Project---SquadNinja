import { SpProfile } from './../../services/spprofileser/spprofile.model';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { SpprofileserService } from 'src/app/services/spprofileser/spprofileser.service';
import { ActivatedRoute } from '@angular/router';
import { FormControl } from '@angular/forms';
import { IRole } from 'src/app/role';
import { Observable } from 'rxjs';
import { IdeahamsterService } from 'src/app/services/ideaser/ideahamster.service';
import { map, startWith } from 'rxjs/operators';
import { ISkill } from 'src/app/skill';
import { MatAutocomplete, MatAutocompleteSelectedEvent, MatChipInputEvent } from '@angular/material';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { IDomain } from 'src/app/domain';
import { ISubDomain } from 'src/app/subdomain';

@Component({
  selector: 'app-spprofile',
  templateUrl: './spprofile.component.html',
  styleUrls: ['./spprofile.component.scss']
})
export class SpprofileComponent implements OnInit {
  
  public domains: IDomain[] = [];
  public subdomains: any[];

  myControls = new FormControl();
  filteredOptions1: Observable<ISubDomain[]>;

  myControl = new FormControl();
  filteredRoles: Observable<IRole[]>;

  public roles: any[];
  public skills:any;
  private skillsArray: any[];

  
  skillCtrl = new FormControl();
  filteredSkills: Observable<ISkill[]>;

  @ViewChild('skillInput', { static: false }) skillInput: ElementRef<HTMLInputElement>;
  @ViewChild('auto2', { static: false }) matAutocomplete: MatAutocomplete;


  // serviceProviderDataskillCtrl: any;
  serviceProviderData={
    name:"",mobileNo:"",domain:"",subDomain:"",currentLocation:"",preferredLocation:"",role:"",skills:"",experience:"",chargePerHour:""
 };

 updated: any;
 public emailId='';
  // private dialog: MatDialog
  constructor(private serviceProviderProfile: SpprofileserService, private route: ActivatedRoute,private _ideahamsterservice: IdeahamsterService) {
    
   }
  user : SpProfile = new SpProfile();
 
  ngOnInit() {
    this.emailId =localStorage.getItem("emailId");
    
    this._ideahamsterservice.getDomains()
    .subscribe(data => {
      this.domains = data
    });

    this._ideahamsterservice.getSubDomains()
      .subscribe(data => {
        this.subdomains = data.map(e => e.name);
        this.filteredOptions1 = this.myControls.valueChanges
          .pipe(
            startWith(''),
            map(value => this._filter1(value))
          );

      });

    this._ideahamsterservice.getroles()
    .subscribe(data => {
      this.roles = data.map(m => m.rolename);
      this.filteredRoles = this.myControl.valueChanges
        .pipe(
          startWith(''),
          map((role: string | null) => role ? this._filter(role) : this.roles.slice())
        );
    });


    this._ideahamsterservice.getSkills()                  
    .subscribe(data => {
    this.skillsArray = data.map(e => e.name);
    // console.log(this.skillsArray);
    this.filteredSkills = this.skillCtrl.valueChanges
        .pipe(
          startWith(null),
          map((skill: string | null) => skill ? this._filter2(skill) : this.skillsArray.slice()));
    });
    this.skills = [];

    this.getTheProfile();
  }

  
  getTheProfile(){
    this.serviceProviderProfile.getByEmailIdForServiceProvider(this.emailId)
    .subscribe((data)=> {
      // console.log("data fetched..", data);
      this.serviceProviderData=data;
      
      console.log("after getting back from service",this.serviceProviderData);
    });

 //  console.log(this.filteredSkills);
  
       
}

private _filter(value: any) {
  const filterValue = value.toLowerCase();
  return this.roles.filter(role => role.toLowerCase().includes(filterValue));
  
}

  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];

  

  add(event: MatChipInputEvent): void {
    console.log("added");
    if (!this.matAutocomplete.isOpen) {
      const input = event.input;
      const value = event.value;

      
      if ((value || '').trim()) {
        this.skills.push(value.trim());
      }

      // Reset the input value
      if (input) {
        input.value = '';
      }

      this.skillCtrl.setValue(null);
    }
  }

  remove(skill: any): void {
    const index = this.skills.indexOf(skill);

    if (index >= 0) {
      this.skills.splice(index, 1);
    }
  }

  onSubmitUpdate(){
    let this1=this;
    console.log("this.serviceProviderData", this.serviceProviderData);
    // console.log("this.user******", this.serviceProviderData);
    this.serviceProviderProfile.updateTheProfile(this1.serviceProviderData,this.emailId).subscribe((data)=> {
      console.log("data updated..", data);
      this1.updated=data;
      this1.getTheProfile();
      console.log("after getting back from service",this1.updated);
  }, err => {
    console.log(err);
  });

  // onSubmitUpdate(){
  //   let this1=this;
  //   console.log("this.serviceProviderData",this.serviceProviderProfile.updateTheProfile(this1.serviceProviderData).subscribe);
  //   // console.log("this.user******", this.serviceProviderData);
  //   this.serviceProviderProfile.updateTheProfile(this1.serviceProviderData).subscribe(data => console.log("*****", data));
    
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.skills.push(event.option.viewValue);
    this.skillInput.nativeElement.value = '';
    this.skillCtrl.setValue(null);
  }


  private _filter2(value2: any) {
    const filterValue2 = value2.toLowerCase();
    return this.skillsArray.filter(skill => skill.toLowerCase().indexOf(filterValue2) === 0);
  }
  private _filter1(value: any) {
    const filterValue = value.toLowerCase();
    return this.subdomains.filter(subdomain => subdomain.toLowerCase().includes(filterValue));
  }




    
  
}
