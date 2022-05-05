import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Payment } from 'src/app/model/payment';

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
    getPaymentByContrat( id : string): Observable <Payment[]>{
      
      return this.httpClient.get<Payment[]>('http://localhost:8087/MITMVC/payment/getPaymentByContrat/'+id);
      }

addPayment(id : string , payment : Payment) : Observable <Payment>{

  return this.httpClient.post<Payment>('http://localhost:8087/MITMVC/payment/add-payment/'+id,payment);
}

   downloadPDF(): any {
      
    return this.httpClient.get('http://localhost:8087/MITMVC/pdfexport/pdf/generate');
        
  }


editPayment(payment : Payment): Observable<Payment> {
  return this.httpClient.put<Payment>('http://localhost:8087/MITMVC/payment/modify-Payment', payment)
}
deletePayment(payment: Payment | number): Observable<Payment> {
  const id = typeof payment=== 'number' ? payment: payment.IDPayment;
  const url='http://localhost:8087/MITMVC/payment/delete-Payment/'+id;
  return this.httpClient.delete<Payment>(url);
  }


  pourcentageRemainingAmount(StartDate: Date, EndDate:Date){
    return this.httpClient.get<number>('http://localhost:8087/MITMVC/payment/get-pourcentage/'+StartDate+'/'+EndDate);
  }

}
