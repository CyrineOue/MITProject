import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DataTableDirective } from 'angular-datatables';
import { Observable, Subject } from 'rxjs';
import { Sinister } from 'src/app/model/sinister';
import { SinisterService } from 'src/app/shared/sinister/sinister.service';

@Component({
  selector: 'app-sinisters',
  templateUrl: './sinisters.component.html',
  //styleUrls: ['./sinisters.component.scss']
})
export class SinistersComponent implements OnInit {

  listSinisters: Sinister[];
  listSinisters$: Observable<Sinister[]>;
  closeResult! : string;
  form : boolean = false;
  sinister : Sinister = new Sinister();

  dtOptions: DataTables.Settings={};
  dtTrigger: Subject<any> = new Subject();

  @ViewChild(DataTableDirective) dtElement: DataTableDirective;

  constructor(private sinisterservice : SinisterService, private modalService: NgbModal) {}

  ngOnInit(): void {
    this.getAllSinisters();
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
    {
      this.dtOptions = {
        pagingType: 'full_numbers',
        pageLength:5,
        autoWidth: true,
        order: [[2,'desc']]
      };
      this.listSinisters$ = this.sinisterservice.getSinisters();
      this.listSinisters$.subscribe(result => {this.listSinisters = result;
                                    this.dtTrigger.next;
    });}
  
  }
  getAllSinisters(){
    this.sinisterservice.getSinisters().subscribe(res => {console.log(res); this.listSinisters= res});
  }

  deleteSinister(id:number){
    this.sinisterservice.deleteSinisterById(id).subscribe(() => this.listSinisters= [(this.sinister.idSinister,
      this.sinister),... this.listSinisters]);
  }

  editSinister(sinister: Sinister){
    this.sinisterservice.editSinister(sinister).subscribe();
  }
  

  addSinister(){
    this.sinisterservice.addSinister(this.sinister).subscribe(() => {this.listSinisters= [(this.sinister.idSinister,
      this.sinister),... this.listSinisters];
      this.form = false;}
    );
  }

  open(content: any) {
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
