export class SearchData {
    roleName: string;
    serviceProviderList : [{
                name : string;
                mobileNo : number;
                email : string;
                domain : string;
                role : [{
                    role : string,
                    skills : [string],
                    experience : string
                }];
                workedIdeas : [string];
                chargePerHour : string;
                currentLocation : string;
                preferredLocation : [string];
     }];
}