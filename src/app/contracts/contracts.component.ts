import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Contract } from 'src/app/model/Contract';
import { ContractService } from 'src/app/shared/contract/contract.service';

@Component({
  selector: 'app-contracts',
  templateUrl: './contracts.component.html',
  //styleUrls: ['./contracts.component.scss']
})
export class ContractsComponent implements OnInit {
  [x: string]: any;

  listContracts : Contract [];
  form : boolean = false;
  closeResult! : string;
  contract!: Contract;
  constructor(private contractService : ContractService, private modalService :NgbModal) { }

  ngOnInit(): void {
    
    this.getContracts();
    this.contract ={
      IDContract : null,
      CreationDate : null, 
      StartDate : null, 
      EndDate : null, 
      NetPremium : null,
      TTCPremium: null, 
      CeillingAmount: null, 
      InstallmentsNB: null, 
      Costatus: null,
      Method: null, 
      IDClientP: null, 
      IDClientC: null,
      Payments:null
     
    }

  }

  getContracts() {
    this.contractService.getContracts().subscribe(res => {console.log(res); this.listContracts= res});
  }

  addContract(contract : Contract){
    this.contractService.addContract(contract).subscribe(() => { this.getContracts(); this.form=false;});
  }
  editContract(contract: Contract){
    this.contractService.editContract(contract).subscribe();
  }


  deleteContract(idContract : any){
    this.contractService.deleteContract(idContract).subscribe(() => this.getContracts())
  }
  

  // PaymentIntent(){

  // this.contractService.PaymentIntent().subscribe(res => {console.log(res)}); 
  // }

  // chargeCard(token: string) {
  //   const headers = new Headers({'token': token, 'amount': 100});
  //   this.http.post('http://localhost:8087/APIPayment/chargestripe', {}, {headers: headers})
  //     .subscribe(resp => {
  //       console.log(resp);
  //     })
  //   }

  chargeCard(token: string) {
    var myFormData = { token: 'token', amount: '1000' };
    const headers = new HttpHeaders();
  headers.append('Content-Type', 'application/json');
      //HTTP POST REQUEST
      return this.httpClient.post('http://localhost:8087/APIPayment/chargestripe',  { token: 'token', amount: '1000' }, {headers: headers});


   }
  
  chargeCreditCard(): void {
    let form = document.getElementsByTagName("form")[0];
    (<any>window).Stripe.card.createToken({
      number: form.cardNumber.value,
      exp_month: form.expMonth.value,
      exp_year: form.expYear.value,
      cvc: form.cvc.value
    }, (status: number, response: any) => {
     if (status === 200) {
        let token = response.id;
        this.chargeCard(token);
      } else {
      console.log(response.error.message);
      }
    });
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
