export class SpProfile{
   name : string;
   mobileNo : number;
   email : string;
   domain : string;
   role : [{
       role : string,
       skills : [string],
       experience : string
   }];
   invitedIdeas : [string];
   workedIdeas : [string];
   chargePerHour : string;
   currentLocation : string;
   preferredLocation : [string];
}