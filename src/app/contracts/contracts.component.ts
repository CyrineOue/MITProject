import { Component, OnInit } from '@angular/core';
import { Contracts } from '../contracts';

@Component({
  selector: 'app-contracts',
  templateUrl: './contracts.component.html',
  styleUrls: ['./contracts.component.scss']
})
export class ContractsComponent implements OnInit {
  listContracts : any;
  form : boolean = false;
  contract !:Contracts;
  closeResult! : string;

  constructor(private contractService : ContractService, private modalService :NgbModal) { }

  ngOnInit(): void {
    this.getAllContracts();;
    this.contract ={
      IDContract : null;
    }

  }

}
