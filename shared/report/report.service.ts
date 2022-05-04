import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Report } from 'src/app/model/report';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  ReportUrl = "http://localhost:8081/mit/report/retrieve-all-reports";
  constructor(private http : HttpClient) { }
  getReports() : Observable<Report[]>{
    return this.http.get<Report[]>(this.ReportUrl);
  }

  deleteReportById(id:number){
    return this.http.delete<Report>("http://localhost:8081/mit/report/remove-report/"+id);
  }

  editReport(report: Report){
    return this.http.put<Report>("http://localhost:8081/mit/report/modify-report",report);
  }

  addReport(report:Report){
    return this.http.post<Report>("http://localhost:8081/mit/report/add-report",report);
    }

  exportPdfReport(id:number): Observable<Blob>{
    return this.http.get("http://localhost:8081/mit/pdf/generateReport/"+id, {responseType: 'blob'});
  }

  getTurnover() : Observable<number[]>{
    return this.http.get<number[]>("http://localhost:8081/mit/report/retrieve-turnover");
  }

  getStartDate(from:Date,to:Date) : Observable<Report[]>{
    return this.http.get<Report[]>("http://localhost:8081/mit/report/retrieve-report-ByDate/"+from+"/"+to);
  }

  searhhdata(model : any){  
    debugger;    
   return this.http.post<any>('http://localhost:1141/Api/Searchdata/search',model);    
  }  
  showdata(){  
    debugger;    
   return this.http.get<any>('http://localhost:1141/Api/Searchdata/showdata');    
  }  
}
