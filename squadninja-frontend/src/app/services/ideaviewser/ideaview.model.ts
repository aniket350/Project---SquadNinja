export class IdeaView  {
    id: number;
    title: string;
    description: string;
    duration: string;
    domain: string;
    subDomain: string;
    cost: number;
    role: [
        {
            experience: string,
            noOfPeople: number,
            skills: [string,string],
            roleName: string
        }
    ];
    appliedTeam: [
        {
            name: string;
            mobileNumber: number;
            emailId: string;
            skills: [
                string,
                string
            ],
            chargePerHour: number
        }
    ];
    invitedTeam: [
        {
            name: string;
            mobileNumber: number;
            emailId: string;
            skills: [
                string,
                string
            ],
            chargePerHour: number
        }
    ];
    selectedTeam: [
        {
            name: string;
            mobileNumber: number;
            emailId: string;
            skills: [
                string,
                string
            ],
            chargePerHour: number
        },
        {
            name : string;
            mobileNumber: 12345678,
            emailId: string;
            skills: [
                string,
                string
            ],
            chargePerHour: number;
        }
    ];
    status: string;
    postedOn: number;
    postedBy: string;
    location: string;
} 
