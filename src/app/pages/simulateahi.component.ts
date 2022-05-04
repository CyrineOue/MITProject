import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ProductService } from 'src/app/shared/product/product.service';

@Component({
  selector: 'app-simulateahi',
  templateUrl: './simulateahi.component.html',
  //styleUrls: ['./simulateahi.component.scss']
})
export class SimulateahiComponent implements OnInit {

  calcul:number;
  closeResult! : string;
  form : boolean = false;
  fieldvalue:number;
  location:string;
  fieldsize:number;

  constructor(private productService:ProductService,private modalService: NgbModal) { }

  ngOnInit(): void {
    this.fieldvalue=null;
  }

  simulateAHI(fv:number,l:string,fs:number){
    this.productService.simulAHI(fv,l,fs).subscribe(res =>{console.log(res);this.calcul=res});

  }

}
