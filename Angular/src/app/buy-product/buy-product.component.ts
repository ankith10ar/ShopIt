import { Component, OnInit } from '@angular/core';
import {Carts} from "../cart-table/model/carts";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {AppComponent, UserInfo} from "../app.component";
import {NavbarComponent} from "../navbar/navbar.component";

@Component({
  selector: 'app-buy-product',
  templateUrl: './buy-product.component.html',
  styleUrls: ['./buy-product.component.css']
})
export class BuyProductComponent implements OnInit {

  listCart: Carts[] = [];
  constructor(private http: HttpClient, private router:Router) { }
  user: UserInfo = new class implements UserInfo {
    firstname: string;
    lastname: string;
    password: string;
    phone: string;
    userid: number;
    username: string;
  };
  total: number;
  
  ngOnInit() {
    if (localStorage.getItem("userData")==null) {
      this.router.navigate(['login']);
      NavbarComponent.danger="Please Login/Register first";
      NavbarComponent.checkAllMethods();
    }
    else {
      this.getAllCart();
      this.getTotal();
    }
  }

  public getAllCart()
  {
    let userData = JSON.parse(localStorage.getItem("userData"));
    Object.assign(this.user,userData);

    let url = "http://localhost:8080/cart/"+this.user.userid;
    this.http.get<Carts[]>(url).subscribe(
      res => {
        this.listCart = res;
        if(this.listCart.length==0) {
          this.router.navigate(['home']);
        }
      },
      err => {
        alert("An error has occured")
      }
    );
  }

  public getTotal()
  {
    let userData = JSON.parse(localStorage.getItem("userData"));
    Object.assign(this.user,userData);

    let url = "http://localhost:8080/getTotal/"+this.user.userid;
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
