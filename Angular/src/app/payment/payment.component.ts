import { Component, OnInit } from '@angular/core';
import {Carts} from "../cart-table/model/carts";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {AppComponent, UserInfo} from "../app.component";
import {NavbarComponent} from "../navbar/navbar.component";
import {ip} from "../global";

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  total: number;

  public payuform: any = {};
  disablePaymentButton: boolean = true;

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

    let url = ip+"/payment/"+this.user.userid;
    this.http.get<number>(url).subscribe(
      res => {
        this.total = res;
        if (this.total==0)
          this.router.navigate(['home']);
        else
          this.payuform.amount = this.total;
      },
      err => {
        alert("An error has occured")
      }
    );

    this.payuform.email = this.user.username;
    this.payuform.firstname = this.user.firstname;
    this.payuform.phone = this.user.phone;
    this.payuform.productinfo = this.user.username;

  }

  confirmPayment() {
    const paymentPayload = {
      email: this.payuform.email,
      name: this.payuform.firstname,
      phone: this.payuform.phone,
      productInfo: this.payuform.productinfo,
      amount: this.payuform.amount
    };
    return this.http.post<any>(ip+'/payment/payment-details', paymentPayload).subscribe(
      data => {
        console.log(data);
        this.payuform.txnid = data.txnId;
        this.payuform.surl = data.sUrl;
        this.payuform.furl = data.fUrl;
        this.payuform.key = data.key;
        this.payuform.hash = data.hash;
        this.payuform.txnid = data.txnId;
        this.disablePaymentButton = false;
      }, error1 => {
        console.log(error1);
      })
  }

}
