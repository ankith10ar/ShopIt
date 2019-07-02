import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { CartTableComponent } from './cart-table/cart-table.component';
import { ProductTableComponent } from './product-table/product-table.component';
import {Router, RouterModule, Routes} from "@angular/router";
import { NotFoundComponent } from './not-found/not-found.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { BuyProductComponent } from './buy-product/buy-product.component';
import { PaymentComponent } from './payment/payment.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import {AngularRaveComponent, AngularRaveModule} from "angular-rave";

const appRoutes :Routes = [
  {
    path:'home',
    component: ProductTableComponent
  },
  {
    path: 'cart',
    component: CartTableComponent
  },
  {
    path: 'buy',
    component: BuyProductComponent
  },
  {
    path: 'payment',
    component: PaymentComponent
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: '',
    component: ProductTableComponent,
    pathMatch: 'full'
  },
  {
    path:'**',
    component: NotFoundComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    CartTableComponent,
    ProductTableComponent,
    NotFoundComponent,
    BuyProductComponent,
    PaymentComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes),
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
