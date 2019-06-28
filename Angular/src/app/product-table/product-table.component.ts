import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "./model/product";
import {Carts} from "../cart-table/model/carts";
import {NavbarComponent} from "../navbar/navbar.component";
import {AppComponent, UserInfo} from "../app.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-product-table',
  templateUrl: './product-table.component.html',
  styleUrls: ['./product-table.component.css']
})
export class ProductTableComponent implements OnInit {

  listProd: Product[] = [];
  constructor(private http: HttpClient,private router:Router) { }
  size: number;
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
      this.getAllProducts();
      this.getCartSize();
    }
  }

  public getCartSize()
  {
    let userData = JSON.parse(localStorage.getItem("userData"));
    Object.assign(this.user,userData);

    let url = "http://localhost:8080/cartSize/"+this.user.userid;
    this.http.get<number>(url).subscribe(
      res => {
        this.size = res;
      },
      err => {
        alert("An error has occured")
      }
    );
  }

  public buyProduct(id: number)
  {
    let userData = JSON.parse(localStorage.getItem("userData"));
    Object.assign(this.user,userData);

    let url = "http://localhost:8080/buyProd/"+id+"/"+this.user.userid;
    this.http.get<boolean>(url).subscribe(
      res => {
        //location.reload();
        this.ngOnInit();
        if (res==true) {
          NavbarComponent.success = "Added the product";
          NavbarComponent.checkAllMethods();
        }
        else
        {
          NavbarComponent.warning = "Product does not exist";
          NavbarComponent.checkAllMethods();
        }
      },
      err => {
        alert("Not able to buy")
      }
    )
  }

  public getAllProducts(){
    let url = "http://localhost:8080/home";
    this.http.get<Product[]>(url).subscribe(
      res => {
          this.listProd = res;
      },
      err => {
        alert("An error has occured")
      }
    );
  }

}
