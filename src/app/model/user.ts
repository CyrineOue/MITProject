import { Role } from "./Role";

export class User {
    idLog: number;
    role: Role;
    email: string;
    password : string;
    active: boolean;  
}