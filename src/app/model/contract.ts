import { Payment } from "./payment";
import { Product } from "./product";

export class Contract {
    IDContract: string; 
   CreationDate: Date;
   StartDate: Date;
   EndDate: Date;
   NetPremium: number;
   TTCPremium: number;
   CeillingAmount: number;
   InstallmentsNB: number;
   costatus: string;
   Method: string;
   IDClientP: string;
   IDClientC: string;
   Payments : Payment;
   coproduct : Product;
   }