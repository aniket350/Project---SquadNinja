export class IdeaView  {
    id: string;
    title: string;
    description: string;
    duration: string;
    domain: string;
    subDomain: string;
    cost: number;
    role: [
        {
            experience: string,
            noOfPeople: string,
            skills: [string,string],
            role: string
        }
    ];
    appliedTeam: [
        {
            name: string,
            email: string,
            mobileNo: number,
            role : [
                {
                    role : string,
                    skills : [string],
                    experience : string,
                }];
                chargePerHour : string
            }
        ]; 
    invitedTeam: [
        {
            name: string,
            email: string,
            mobileNo: number,
            role : [
                {
                    role : string,
                    skills : [string],
                    experience : string,
                }];
                chargePerHour : string
            }
        ]; 
    selectedTeam: [
        {
            name: string,
            email: string,
            mobileNo: number,
            role : [
                {
                    role : string,
                    skills : [string],
                    experience : string,
                }];
                chargePerHour : string
            }
        ]; 
    status: string;
    postedOn: string;
    postedBy: string;
    location: string;
} 
