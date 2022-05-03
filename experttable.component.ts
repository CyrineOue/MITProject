import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Expert } from 'src/app/model/expert';
import { ExpertService } from 'src/app/shared/expert/expert.service';

@Component({
  selector: 'app-experttable',
  templateUrl: './experttable.component.html',
  styleUrls: ['./experttable.component.scss']
})
export class ExperttableComponent implements OnInit {

  closeResult! : string;
  form : boolean = false;
  listExperts: Expert[];
  expert: Expert = new Expert();
  constructor( private expertservice : ExpertService, private modalService: NgbModal) {}

  ngOnInit(): void {
    this.getAllExperts();
    this.expert = {
      idExpert: null,
      name: null,
      lastName: null,
      phoneNb: null,
      expertiseField: null,
      logExpert:{
        idLog:null,
        email:null,
        password:null,
        active:null,
        role:null
      }
  }

}

getAllExperts(){
  this.expertservice.getExperts().subscribe(res => {console.log(res); this.listExperts= res});
}

deleteExpert(id:number){
  this.expertservice.deleteExpertById(id).subscribe(() => this.listExperts= [(this.expert.idExpert,
    this.expert),... this.listExperts]);
}

editExpert(expert: Expert){
  this.expertservice.editExpert(expert).subscribe();
}

addExpert(){
  this.expertservice.addExpert(this.expert).subscribe(() => {this.listExperts= [(this.expert.idExpert,
    this.expert),... this.listExperts];
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
