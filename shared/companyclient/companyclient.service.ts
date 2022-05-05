import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CompanyClient } from 'src/app/model/companyclient';

@Injectable({
  providedIn: 'root'
})
export class CompanyclientService {

  CompanyClientUrl = "http://localhost:8081/mit/companyclient";
  constructor(private http : HttpClient) { }
  getCompanyClients() : Observable<CompanyClient[]>{
    return this.http.get<CompanyClient[]>(this.CompanyClientUrl+"/retrieve-all-companyclients");
  }

  deleteCompanyClient(id:number){

    return this.http.delete(`${this.CompanyClientUrl}/archive-companyclient/${id}`);
    }

  editCompanyClient(companyClient: CompanyClient){
    return this.http.put<CompanyClient>(this.CompanyClientUrl+"/modify-companyclient/",companyClient);
  }

  addCompanyClient(companyClient:CompanyClient){
    return this.http.post<CompanyClient>(this.CompanyClientUrl+"/add-companyclient",companyClient);
    }
  getScoreC(id :number){
      return this.http.get(`${this.CompanyClientUrl}/score-companyClient/${id}`)
    }
  getCategoryC(id :number){
    return this.http.get(`${this.CompanyClientUrl}/categorise-companyClient/${id}`)
  }
  getIdealClientC() {
    return this.http.get<CompanyClient>(`${this.CompanyClientUrl}/GetIdealCompanyClient`);
  }
  GetIdIdealCompanyClient(){
    return this.http.get<number>(`${this.CompanyClientUrl}/GetIdIdealCompanyClient`);
  }
}
