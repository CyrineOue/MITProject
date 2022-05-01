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


addPayment(payment : Payment) : Observable <Payment>{
  const id = payment.copayment.IDContract;
  return this.httpClient.post<Payment>('http://localhost:8087/MITMVC/payment/add-payment/'+id,payment);
}

   downloadPDF(): any {
      
    return this.httpClient.get('http://localhost:8087/MITMVC/pdfexport/pdf/generate');
        
  }


editPayment(payment : Payment): Observable<Payment> {
  return this.httpClient.put<Payment>('http://localhost:8087/MITMVC/payment/modify-Payment', payment)
}

}