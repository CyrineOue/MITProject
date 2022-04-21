import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserService } from '../shared/user/user.service';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.scss']
})
export class AuthenticationComponent implements OnInit {
  user: any = {};
  email: string;
  password: String;
  errorMessage: string;
  name: string;
  Wdate;
  annee: 0;
  constructor(private router: Router, private userService: UserService, public toastr: ToastrService) { }
  ngOnInit() {
    this.userService.islogin = false;
    this.userService.admin = false;
    this.userService.client = false;
    this.annee = (this.Wdate).toString().substring(0, 4);
    localStorage.setItem('annee', this.annee.toString());
  }
  login() {

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
  }

}
