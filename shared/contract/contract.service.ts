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
  return this.httpClient.get<Contract[]>('http://localhost:8087/MITMVC/Contract/retrieve-all-contracts');
  }
  addContract(contract : Contract): Observable<Contract> {
    return this.httpClient.post<Contract>('http://localhost:8087/MITMVC/Contract/add-contract', contract)
    }

    editContract(contract : Contract): Observable<Contract> {
      return this.httpClient.put<Contract>('http://localhost:8087/MITMVC/Contract/modify-contract', contract)
      }

      deleteContract(contract: Contract | number): Observable<Contract> {
        const id = typeof contract=== 'number' ? contract: contract.IDContract;
        const url='http://localhost:8087/MITMVC/Contract/remove-Contract/'+id;
        return this.httpClient.delete<Contract>(url);
        }
}
