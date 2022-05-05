import { Admin } from "./admin";
import { Agent } from "./agent";
import { CompanyClient } from "./companyclient";
import { Expert } from "./expert";
import { Role } from "./Role";

export class User {
    idLog: number;
    role: Role;
    email: string;
    password : string;
    active: boolean;
    admin: Admin ;
    agent: Agent; 
    expert: Expert;
    companyClient: CompanyClient;
}