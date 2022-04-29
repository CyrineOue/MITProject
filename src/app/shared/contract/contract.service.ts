import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Contract } from 'src/app/model/Contract';

@Injectable({
  providedIn: 'root'
})
export class ContractService {

   //API_URL = '/api/mit/Contract/';
 
  constructor (private httpClient: HttpClient) {

   }
  getContracts() : Observable <Contract[]>{
  return this.httpClient.get<Contract[]>('http://localhost:8081/mit/Contract/retrieve-all-contracts');
  }


  
}
