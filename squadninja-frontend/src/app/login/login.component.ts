import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../services/userser/userservice.service';
import {Router,CanActivate} from '@angular/router';
import * as jwt_decode from 'jwt-decode';
// import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
// import {  TemplateRef } from '@angular/core';
import { RegisterserService } from '../services/registerser/registerser.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  // private router:Router;
  public decodedTokenWithRoleSub: any;

   // decode token
constructor(private registerser: RegisterserService, private userservice: UserserviceService, private router: Router) { }

  focus;
  focus1;
  // private modalService: BsModalService;
  invalid:boolean=false;
  public role: any;
  // public roles: any;
  public email: any;
  public password: any;
  public token: any;
  public decodedroletoken: any;
  // public modalService: any;
  public ideaHamster:any = "ideaHamster";

  public hello:any;


  ngOnInit() {
  }
   // show decoded token object in console




  getDecodedAccessToken(token: string): any {
    try {
        return jwt_decode(token) ;
    } catch (Error) {
        return Error;
    }
  }
  //
  // canActivate(){
  //   if(this.email!=null){
  //     return true;
  //   }
  //   else
  //   return false;
  // }

  selectIh() {
    this.role = 'ideahamster';
  }

  selectSp() {
    this.role = 'serviceprovider';
  }


Login() {
    this.userservice.Login (this.email, this.password).subscribe((response) => {
      let data = response;
      if (response) {
        localStorage.setItem("emailId",this.email);
        this.token = response;
        this.decodedroletoken = this.getDecodedAccessToken(this.token.token);
        this.decodedTokenWithRoleSub = this.decodedroletoken.sub;

          // canActivate(): boolean {
            
        if (this.decodedTokenWithRoleSub === "ideaHamster") {

          this.router.navigate(['/ihdashboard']);
        }
         else {
          this.router.navigate(['/spdashboard']);
        }
      // }


  }},
  (err) => {
    this.invalid=true;
    console.log(err);

});



}
}
