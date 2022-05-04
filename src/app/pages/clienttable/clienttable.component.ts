import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CompanyClient } from 'src/app/model/companyClient';
import { ParticularClient } from 'src/app/model/particularClient';
import { CompanyClientService } from 'src/app/shared/company Client/company-client.service';
import { ParticularClientService } from 'src/app/shared/particular Client/particular-client.service';

@Component({
  selector: 'app-clienttable',
  templateUrl: 'clienttable.component.html',
  styleUrls: ['clienttable.component.scss']
})
export class ClienttableComponent implements OnInit {
  listCompanyClients: CompanyClient[];
  listParticularClients:ParticularClient[];
  closeResult! : string;
  form : boolean = false;
  clientC : CompanyClient = new CompanyClient();
  clientP :ParticularClient=new ParticularClient();
  IdealClientP:ParticularClient=new ParticularClient();
  dataSource1: MatTableDataSource<CompanyClient>;
  dataSource2: MatTableDataSource<ParticularClient>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  IdealClientC: CompanyClient;
  constructor(private CompanyClientservice: CompanyClientService,private ParticularClientService:ParticularClientService,private modalService: NgbModal) { }
  ngOnInit(): void {
    this.getAllCompanyClients();
    this.clientC={
    idClientC : null,
    brand:null,
    activityArea:null,
    creationDate:null,
    employeesNb:null,
    capital: null,
    nbDeclaredSinistersC:null,
    archived: null,
    sbuscriptionDate:null,
    categoryC: null,
    ScoreC:null,
    logClientC:{
      idLog:null,
      email:null,
      password:null,
      active:null,
      role:null
    }
    }
    this.getAllParticularClients();
    this.clientP={
      idClientP:null,
      firstName:null,
      lastName:null,
      profession:null,
      cin:null,
      birthDate:null,
      gender:null,
      homeAddress:null,
      phoneNo:null,
      nbDeclaredSinistersP:null,
      archived:false,
      sbuscriptionDate :null,
      CategoryP:null,
      logClientP:{
        idLog:null,
        email:null,
        password:null,
        active:null,
        role:null
      },
      ScoreP :null

    }
   
  }
 // ******** Company Client Services ******** \\
  getAllCompanyClients(): void{
    this.CompanyClientservice.getCompanyClients().subscribe(res => {console.log(res); this.listCompanyClients= res});

  }
  deleteCompanyClient(idClientC:number){
    this.CompanyClientservice.deleteCompanyClient(idClientC).subscribe(() =>this.getAllCompanyClients());
  }

  editCompanyClient(clientC: CompanyClient){
    this.CompanyClientservice.editCompanyClient(clientC).subscribe();
  }

  addCompanyClient(clientC : CompanyClient){
    this.CompanyClientservice.addCompanyClient(clientC).subscribe(() => {this.listCompanyClients= [(this.clientC.idClientC,
      this.clientC),... this.listCompanyClients];
      this.form = false;}
    );

  }
  getCategoryC(id : number){
    this.CompanyClientservice.getCategoryC(id).subscribe();
  };
  getScoreC(id :number){
    this.CompanyClientservice.getScoreC(id).subscribe();
  }
  getIdealClientC(){
   // this.IdealClientC= this.CompanyClientservice.getIdealClientC();
    
  }
// ******** Particular Client Services ******** \\
  getAllParticularClients() {
    this.ParticularClientService.getAllParticularClients().subscribe(res => {console.log(res); this.listParticularClients= res});
  };
  deleteParticularClient(id:number){
    this.ParticularClientService.deleteParticularClient(id).subscribe(()=>this.getAllParticularClients())
  }
  editParticularClient(pc: ParticularClient){
    this.ParticularClientService.editParticularClient(pc).subscribe();
  }
  addParticularClient(pc:ParticularClient){
    this.ParticularClientService.addParticularClient(pc).subscribe(() => {this.listParticularClients= [(this.clientP.idClientP,
      this.clientP),... this.listParticularClients];
      this.form = false;}
    );
    }
  getCategoryP(id :number){
    this.ParticularClientService.getCategoryP(id).subscribe();

  }
  getScoreP(id :number){
    this.ParticularClientService.getScoreP(id).subscribe();
  }


// ******** Commun ******** \\
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
