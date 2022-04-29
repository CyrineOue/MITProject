import { HttpClient } from "@angular/common/http"
import { CompanyClient } from "src/app/model/company-client"
const API_URL = 'api/mit/companyclient';
export class CompanyClientService {
    constructor(private httpClient : HttpClient){}
    getAllCompanyClients(){
        return this.httpClient.get(API_URL+`/retrieve-all-companyclients`)
    }
    addCompanyClient(cc: CompanyClient){
        return this.httpClient.post(API_URL+`/add-companyclient`, cc)
    }
    editCompanyClient(cc: CompanyClient){
        return this.httpClient.put(API_URL+`/modify-companyclient`, cc)
    }
    archiveCompanyClient(id: number){
        return this.httpClient.put(API_URL+`/archive-companyclient`, id)
    }
}
