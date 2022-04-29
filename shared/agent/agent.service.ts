import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Agent } from 'src/app/model/agent';

@Injectable({
  providedIn: 'root'
})
export class AgentService {

  agentUrl = "http://localhost:8081/mit/agent/retrieve-all-agents";
  constructor(private http : HttpClient) { }
  getAgents() : Observable<Agent[]>{
    return this.http.get<Agent[]>(this.agentUrl);
  }

  deleteAgentById(id:number){
    return this.http.delete<Agent>("http://localhost:8081/mit/agent/remove-agent/"+id);
  }

  editAgent(agent: Agent){
    return this.http.put<Agent>("http://localhost:8081/mit/agent/modify-admin",agent);
  }

  addAgent(agent:Agent){
    return this.http.post<Agent>("http://localhost:8081/mit/agent/add-agent",agent);
    }
}
