import { Component, OnInit } from '@angular/core';
import {Carts} from "../cart-table/model/carts";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {AppComponent, UserInfo} from "../app.component";
import {NavbarComponent} from "../navbar/navbar.component";

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  total: number;
  constructor(private http: HttpClient,private router:Router) { }
  user: UserInfo = new class implements UserInfo {
    firstname: string;
    lastname: string;
    password: string;
    phone: string;
    userid: number;
    username: string;
  };

  ngOnInit() {
    if (localStorage.getItem("userData")==null) {
      this.router.navigate(['login']);
      NavbarComponent.danger="Please Login/Register first";
      NavbarComponent.checkAllMethods();
    }
    else {
      this.getPayment();
    }
  }

  public getPayment()
  {
    let userData = JSON.parse(localStorage.getItem("userData"));
    Object.assign(this.user,userData);

    let url = "http://localhost:8080/payment/"+this.user.userid;
    this.http.get<number>(url).subscribe(
      res => {
        this.total = res;
        if (this.total==0)
          this.router.navigate(['home']);
      },
      err => {
        alert("An error has occured")
      }
    );
  }

}
