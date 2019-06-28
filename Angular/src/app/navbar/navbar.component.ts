import { Component, OnInit } from '@angular/core';
import {UserInfo} from "../app.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  static success:string;
  static info:string;
  static warning:string;
  static danger:string;
  static username: string;

  user: UserInfo = new class implements UserInfo {
    firstname: string;
    lastname: string;
    password: string;
    phone: string;
    userid: number;
    username: string;
  };

  constructor(private router:Router) {
  }

  get firstname(){
    return NavbarComponent.username;
  }

  get staticSuccess(){
    return NavbarComponent.success;
  }
  get staticInfo(){
      return NavbarComponent.info;
    }
  get staticWarning(){
      return NavbarComponent.warning;
    }
  get staticDanger(){
      return NavbarComponent.danger;
    }

  ngOnInit() {
    NavbarComponent.checkAllMethods();
    this.fillUsername();
  }

  public static checkAllMethods()
  {
    setTimeout(function(){
      NavbarComponent.success = null;
      NavbarComponent.info = null;
      NavbarComponent.warning = null;
      NavbarComponent.danger = null;
    }, 3000
    )
  }

  fillUsername(){
   let userData = JSON.parse(localStorage.getItem("userData"));
    Object.assign(this.user,userData);
    NavbarComponent.username = this.user.firstname;
  }

  checkLogin(): boolean
  {
    return localStorage.getItem("userData")!=null;
  }

  clearLocal() {
    localStorage.clear();
    this.router.navigate(['login']);
    NavbarComponent.username = null;
  }
}
