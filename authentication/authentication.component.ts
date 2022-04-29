import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Role } from '../model/Role';
import { TokenStorageService } from '../shared/user/token-storage.service';
import { UserService } from '../shared/user/user.service';

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
  role: Role;
  @Input() error: string | null;
  constructor(private router: Router, private userService: UserService, public toastr: ToastrService,private tokenStorage: TokenStorageService) { }
  //username = ''
  //password = ''
  invalidLogin = false
  
  

  //constructor(private router: Router, private userService: UserService) { }

  /*ngOnInit() {
  }*/
  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.role = this.tokenStorage.getUser().role;
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
        const tokenInfo = this.tokenStorage.getUser();
        console.log(tokenInfo.role);
        if (tokenInfo.role === "ADMIN") {  
          //this.userService.admin = true;
          this.router.navigate(['log/admin']);
          this.isLoggedIn = true;
          this.invalidLogin = false
        }/*
        else if (tokenInfo.role === "AGENT")  {
          //this.userService.agent = true;
          this.router.navigate(['log/agent']);
          this.isLoggedIn = true;
          this.invalidLogin = false

        }*/
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
