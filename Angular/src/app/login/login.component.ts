import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Alert} from "selenium-webdriver";
import {NavbarComponent} from "../navbar/navbar.component";
import {AppComponent, UserInfo} from "../app.component";
import {ip} from "../global";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginCredentials: loginUser = new class implements loginUser {
    password: string;
    username: string;
  };

  showError: boolean;
  constructor(private http: HttpClient,private router: Router) {
    this.loginCredentials.username='';
    this.loginCredentials.password='';
    this.showError = false;
  }

  ngOnInit() {

    if (localStorage.getItem("userData")!=null)
      this.router.navigate(['home']);
  }

  finishLogin() {
    let url = ip+"/loginas";
    this.http.post<UserInfo>(url,this.loginCredentials).subscribe(
      res => {
        localStorage.setItem("userData",JSON.stringify(res));
        this.router.navigate(['home']);
       NavbarComponent.username = res.firstname;
      },
      err => {
        alert('Server error')
      }
    )


  }


  checkLogin() {
    if(this.loginCredentials.username==null || this.loginCredentials.username==='') {
      this.showError = true;
      return;
    }
    if(this.loginCredentials.password==null || this.loginCredentials.password==='') {
      this.showError = true;
      return;
    }
    let url = ip+"/checkLogin";
    this.http.post<boolean>(url,this.loginCredentials).subscribe(
      res => {
        if (res==true)
          this.finishLogin();
        else if (res==false){
          this.showError = true;
        }
      },
      err => {
        alert('Server error');
      }
    )
  }
}

export interface loginUser {
  username: string,
  password: string
}
