import { Component, OnInit } from '@angular/core';
import {Product} from "../product-table/model/product";
import {HttpClient} from "@angular/common/http";
import {Alert} from "selenium-webdriver";
import {CartProd} from "./model/cart-prod";
import {Carts} from "./model/carts";
import {Router} from "@angular/router";
import {NavbarComponent} from "../navbar/navbar.component";
import {AppComponent, UserInfo} from "../app.component";

@Component({
  selector: 'app-cart-table',
  templateUrl: './cart-table.component.html',
  styleUrls: ['./cart-table.component.css']
})
export class CartTableComponent implements OnInit {

  listCart: Carts[] = [];
  public changed:boolean = false;
  public cardProd:CartProd;
  num:number;
  user: UserInfo = new class implements UserInfo {
    firstname: string;
    lastname: string;
    password: string;
    phone: string;
    userid: number;
    username: string;
  };

  constructor(private http: HttpClient,private router:Router) { }

  ngOnInit() {
    if (localStorage.getItem("userData")==null) {
      this.router.navigate(['login']);
      NavbarComponent.danger="Please Login/Register first";
      NavbarComponent.checkAllMethods();
    }
    else {
      this.getAllCart();
    }
  }

  change_event()
  {
    this.changed = true;
  }

  public getAllCart()
  {
    let userData = JSON.parse(localStorage.getItem("userData"));
    Object.assign(this.user,userData);

    let url = "http://localhost:8080/cart/"+this.user.userid;
    this.http.get<Carts[]>(url).subscribe(
      res => {
        this.listCart = res;
        if(this.listCart.length==0)
        this.router.navigate(['home']);
      },
      err => {
        alert("An error has occured")
      }
    );
  }

  public deleteItem(id: string)
  {
    let userData = JSON.parse(localStorage.getItem("userData"));
    Object.assign(this.user,userData);

    let url = "http://localhost:8080/cartDel/"+id;
    this.http.get(url).subscribe(
      res => {
            this.ngOnInit();
          NavbarComponent.info="Removed item successfully";
          NavbarComponent.checkAllMethods();
      },
      err => {
          alert("An error has occured")
      }
    );
  }

  sendAlert(): void
  {
    //this.cardProd.listCart = this.listCart;

    let url = "http://localhost:8080/cart";
    this.http.post(url,this.listCart).subscribe(
    res => {
      this.ngOnInit();
      NavbarComponent.success="Edited Cart successfully";
      NavbarComponent.checkAllMethods();
    },
    err => {
      alert("An upload error has occured")
    }
  );
  }


}

export interface Cart{
  cartid: string,
  name: string,
  description: string,
  cost: any,
  quantity: number,
  costofeach: any
}
