import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { User } from 'src/app/model/user';

const AUTH_API = 'http://localhost:8081/mit/authenticate';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class UserService {

  /*private baseUrl = 'http://localhost:8081/mit/log';
  islogin = false;
  admin = false;
  client = false;
  constructor(private http: HttpClient) { }
  login(login: String): Observable<User[]> {
    
     return this.http.get<User[]>(`${this.baseUrl}/${login}`);
   } */

   constructor(private httpClient: HttpClient) {}
// Provide username and password for authentication, and once authentication is successful, 
//store JWT token in session
  /*authenticate(email, password) {
    return this.httpClient
      .post<any>("http://localhost:8081/mit/authenticate", { email, password })
      .pipe(
        map(userData => {
          sessionStorage.setItem("email", email);
          let tokenStr = "Bearer " + userData.token;
          sessionStorage.setItem("token", tokenStr);
          return userData;
        })
      );
  }*/
  authenticate(email: string, password: string): Observable<any> {
    return this.httpClient.post(AUTH_API , {
      email,
      password
    }, httpOptions);
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem("email");
    console.log(!(user === null));
    return !(user === null);
  }

  logOut() {
    sessionStorage.removeItem("email");
  }

  getConnectedUser(email: string) : Observable<User>{
    return this.httpClient.get<User>("http://localhost:8081/mit/log/users/"+email);
  }

  editAdminProfile(user: User){
    return this.httpClient.put<User>("http://localhost:8081/mit/log/modify-logadmin",user);
  }

  editExpertProfile(user: User){
    return this.httpClient.put<User>("http://localhost:8081/mit/log/modify-logexpert",user);
  }

  editAgentProfile(user: User){
    return this.httpClient.put<User>("http://localhost:8081/mit/log/modify-logagent",user);
  }

  editCompanyProfile(user: User){
    return this.httpClient.put<User>("http://localhost:8081/mit/log/modify-logcompany",user);
  }

}
