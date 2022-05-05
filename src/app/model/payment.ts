import { Contract } from "./Contract";

export class Payment {
    IDPayment: string;
    PaymentDate: Date;
    PaidPremium: number;
    RemainingPremium: number;
    RefundAmount: number;
    Status: boolean;

    
    copayment: Contract;
}