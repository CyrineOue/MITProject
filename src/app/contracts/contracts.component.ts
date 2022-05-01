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
  chargeCreditCard() {
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
  chargeCard(token: string) {
    const headers = new Headers({'token': token });
    this.httpClient.post('http://localhost:8087/MITMVC/APIPayment/charge', {}, {headers: headers}) .subscribe(resp => {
        console.log(resp);
      })
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
