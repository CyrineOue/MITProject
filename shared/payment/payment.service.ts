import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Payment } from 'src/app/model/Payment';


@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  readonly API_URL ='/api/MITMVC/payment/retrieve-all-payment';
  getpayURL: string;
  constructor(private httpClient: HttpClient) { 

    this.getpayURL='http://localhost:8087/MITMVC/payment/retrieve-all-payment'
  }


  getPayments() : Observable <Payment[]>{
    return this.httpClient.get<Payment[]>('http://localhost:8087/MITMVC/payment/retrieve-all-payment');
    }


}