export class IdeaView  {
    id: string;
    title: string;
    description: string;
    duration: string;
    domain: string;
    subDomain: string;
    cost: string;
    role: [
        {
            experience: string,
            noOfPeople: number,
            skills: [string],
            role: string
        }
    ];
    appliedTeam: [
        {
            name: string;
            mobileNo: string;
            email: string;
            role : [{
                role : string,
                skills : [string],
                experience : string
            }];
            chargePerHour: string
        }
    ];
    invitedTeam: [
        {
            name: string;
            mobileNo: string;
            email: string;
            role : [{
                role : string,
                skills : [string],
                experience : string
            }];
            chargePerHour: string
        }
    ];
    selectedTeam: [
        {
            name: string;
            mobileNo: string;
            email: string;
            role : [{
                role : string,
                skills : [string],
                experience : string
            }];
            chargePerHour: string
        }
       
    ];
    status: string;
    postedOn: string;
    postedBy: string;
    location: string;
} 
