import { Component } from '@angular/core';
import {NavbarComponent} from "./navbar/navbar.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'shopit-ng';
  static userinfo: UserInfo = new class implements UserInfo {
    firstname: string;
    lastname: string;
    password: string;
    phone: string;
    userid: number;
    username: string;
  };
}

export interface UserInfo {
  userid:number,
  username:string,
  password:string,
  firstname:string,
  lastname:string,
  phone:string,
}
