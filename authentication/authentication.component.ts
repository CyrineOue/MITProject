import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Role } from '../model/Role';
import { TokenStorageService } from '../shared/user/token-storage.service';
import { UserService } from '../shared/user/user.service';
//import * as jwt_decode from "jwt-decode";
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.scss']
})
export class AuthenticationComponent implements OnInit {
  user: any = {};
  email: string;
  password: string;
  /*errorMessage: string;
  name: string;
  Wdate;
  annee: 0;*/
  form: any = {
    email: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  private roles: string[];
  authority:string='';
  sub:string=null;
  token: string;
  @Input() error: string | null;
  constructor(private router: Router, private userService: UserService, public toastr: ToastrService,private tokenStorage: TokenStorageService) { }
  //username = ''
  //password = ''
  invalidLogin = false
  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    } catch(Error) {
      return null;
    }
  }
  
  

  //constructor(private router: Router, private userService: UserService) { }

  /*ngOnInit() {
  }*/
  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.token=sessionStorage.getItem('auth-user')
      const tokenInfo = this.getDecodedAccessToken(this.token);
      this.roles[0] = tokenInfo.roles[0];
    }
  }

  /*checkLogin() {
    (this.userService.authenticate(this.email, this.password).subscribe(
      data => {
        this.router.navigate(['log/admin'])
        this.invalidLogin = false
      },
      error => {
        this.invalidLogin = true
        this.error = error.message;

      }
    )
    );
  }*/

  checkLogin() {
   // const { email, password } = this.form;
    (this.userService.authenticate(this.email, this.password).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);
        //const token = this.tokenStorage.getUser();
        //const token = localStorage.getItem("token");
        //localStorage.setItem('token', data.body);
          /*A chaque fois on besoin du token */
          this.token=sessionStorage.getItem('auth-user');
          const tokenInfo = this.getDecodedAccessToken(this.token);
        //const tokenInfo = this.getDecodedAccessToken(token);
        console.log(this.token);
        console.log(tokenInfo);
        if (tokenInfo.roles[0].authority==='ROLE_ADMIN') {  
          //this.userService.admin = true;
          this.router.navigate(['log/admin']);
          this.isLoggedIn = true;
          this.invalidLogin = false
        }
        else if (tokenInfo.roles[0].authority==='ROLE_AGENT')  {
          //this.userService.agent = true;
          this.router.navigate(['log/agent']);
          this.isLoggedIn = true;
          this.invalidLogin = false
        }
        else if (tokenInfo.roles[0].authority==='ROLE_EXPERT')  {
          //this.userService.agent = true;
          this.router.navigate(['log/expert']);
          this.isLoggedIn = true;
          this.invalidLogin = false
        }
        else if (tokenInfo.roles[0].authority==='ROLE_COMPANYCLIENT' || tokenInfo.roles[0].authority==='ROLE_ParticularCLIENT' )  {
          //this.userService.agent = true;
          this.router.navigate(['log/client']);
          this.isLoggedIn = true;
          this.invalidLogin = false
        }

        //this.router.navigate(['log/admin'])
        //this.invalidLogin = false
      },
      error => {
        this.invalidLogin = true
        this.error = error.message;
        this.toastr.warning('Login Incorrecte ')

      }
    )
    );
  }
  //ngOnInit() {
    //this.userService.islogin = false;
    //this.userService.admin = false;
    //this.userService.client = false;
    //this.annee = (this.Wdate).toString().substring(0, 4);
    //localStorage.setItem('annee', this.annee.toString());
  //}
  /*login() {

    this.userService.login(this.email).subscribe(
      response => {
        this.user = response;

        if (this.user.pwd == this.password) {
          //this.email = this.user.email;
          //localStorage.setItem('email', this.email);
          this.userService.islogin = true;
          if (this.user.role == "ADMIN") {
            //this.userService.admin = true;
            this.router.navigate(['/log/admin']);
          }
          else {
            //this.userService.client = true;
            this.router.navigate(['/log/client']);
          }
        }
        else {
          this.toastr.warning('Mot de Passe  Incorrecte ')
        }

      },
      error =>

        this.toastr.warning('Login Incorrecte ')
    );
  }*/
 /* onSubmit(): void {
    const { email, password } = this.form;
    this.userService.login(email, password).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.reloadPage();
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }*/
  reloadPage(): void {
    window.location.reload();
  }



}
