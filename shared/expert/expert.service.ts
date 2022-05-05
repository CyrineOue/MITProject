import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Expert } from 'src/app/model/expert';

@Injectable({
  providedIn: 'root'
})
export class ExpertService {

  expertUrl = "api/mit/expert/retrieve-all-experts";
  constructor(private http : HttpClient) { }
  
  getExperts() : Observable<Expert[]>{
    return this.http.get<Expert[]>(this.expertUrl);
  }

  deleteExpertById(id:number){
    return this.http.delete<Expert>("api/mit/expert/remove-expert/"+id);
  }

  editExpert(expert: Expert){
    return this.http.put<Expert>("api/mit/expert/modify-expert",expert);
  }

  addExpert(expert:Expert){
    return this.http.post<Expert>("api/mit/expert/add-expert",expert);
    }
}
