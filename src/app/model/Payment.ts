import { Contract } from "./contract";

export class Payment {
    IDPayment: number;
    PaymentDate: Date;
    PaidPremium: number;
    RemainingPremium: number;
    RefundAmount: number;
    Status: boolean;

    
    copayment: Contract;
}