import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Payment } from 'src/app/model/Payment';
import { PaymentService } from 'src/app/shared/payment/payment.service';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  //styleUrls: ['./payments.component.scss']
})
export class PaymentsComponent implements OnInit {
listPayments : Payment[];
form : boolean = false;
  closeResult! : string;
  payment!:Payment;
  constructor(private paymentService : PaymentService, private modalService :NgbModal) { }

  ngOnInit(): void {
    this.getPayments();
  }
getPayments(){
  this.paymentService.getPayments().subscribe(res => {console.log(res); this.listPayments= res});
}
addPayment(payment: Payment){
this.paymentService.addPayment(payment).subscribe(() => { this.getPayments(); this.form=false;});

}
downloadPDF(){
  this.paymentService.downloadPDF().subscribe(res => {console.log(res); this.listPayments= res});
}
editPayment(payment :Payment){
this.paymentService.editPayment(payment).subscribe;

}
open(content: any): void {
  this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
    this.closeResult = `Closed with: ${result}`;
  }, (reason) => {
    this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
  });
  }
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }
  closeForm(){

  }
  cancel(){
    this.form = false;
  }
}
