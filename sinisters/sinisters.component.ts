import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Sinister } from 'src/app/model/sinister';
import { SinistersService } from 'src/app/shared/sinister/sinisters.service';

@Component({
  selector: 'app-sinisters',
  templateUrl: './sinisters.component.html',
  //styleUrls: ['./sinisters.component.scss']
})
export class SinistersComponent implements OnInit {
  listSinisters : any; 
  form : boolean = false;
  sinister!: Sinister;
  closeResult! : string;
  
  constructor(private sinisterService: SinistersService , private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getSinisters();

    this.sinister = {
      idSinister: null,
      typeSinister: null,
      declarationDate: null,
      indemnisationDate: null,
      sinisterDate: null,
      declarationDelay: null,
      expertJudgement: null,
      damageAmount: null,
      sinisterStatus: null,
      sinisterLocation: null,
      sinisterDescription: null,
      causeOfRejection: null,
      isFraud: null,
      refundPercentage: null,
      refundAmount: null,
      logSinister: null
    }
  }

  getSinisters(){
    this.sinisterService.getSinisters().subscribe(res => this.listSinisters = res)
  }

  addSinister(s: Sinister){
    this.sinisterService.addSinister(s).subscribe(() => {
      this.getSinisters();
      this.form = false;
    });
  }

  editProduct(sinister : Sinister){
    this.sinisterService.editSinister(sinister).subscribe();
  }
  deleteProduct(idSinister : number){
    this.sinisterService.deleteSinisterById(idSinister).subscribe(() => this.getSinisters())
  }

}
