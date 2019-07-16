import { Component, OnInit } from '@angular/core';
import {AppComponent, UserInfo} from "../app.component";
import {HttpClient} from "@angular/common/http";
import set = Reflect.set;
import {NavbarComponent} from "../navbar/navbar.component";
import {Router} from "@angular/router";
import {ip} from "../global";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  userinfo: UserInfo;
  confirmPassword: string;
  emailUsed: boolean;
  emailError: boolean;
  passwordError: boolean;
  phoneError: boolean;
  invalidPhone: boolean;
  firstNameNotEntered: boolean;
  passwordNotEntered: boolean;
  phoneNotEntered: boolean;
  pwdStrength: number;
  phoneVerify: PhoneVerify;

  constructor(private http:HttpClient, private router: Router) {
    this.userinfo = AppComponent.userinfo;
    this.userinfo.phone = '';
    this.confirmPassword='';
    this.emailUsed = false;
    this.emailError = false;
    this.passwordError = false;
    this.phoneError = false;
    this.invalidPhone = false;
    this.firstNameNotEntered = false;
    this.passwordNotEntered = false;
    this.phoneNotEntered = false;
    this.pwdStrength = -1;
  }

  ngOnInit() {
    if (localStorage.getItem("userData")!=null)
      this.router.navigate(['home']);

  }


  emailValidation(): boolean {
    let matcher = new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
    return (matcher.test(this.userinfo.username));
  }


   emailCheck() {
    this.emailError = false;
    if (this.userinfo.username==='') {
      this.emailUsed = false;
      this.emailError = false;
      return;
    }
    let url = ip+"/emailcheck/"+this.userinfo.username;
    this.http.get<boolean>(url).subscribe(
      res => {
        console.log(res);
       this.emailUsed = res;
      },
      err => {
        alert("server error")
      }
    );
  }

  passwordCheck() {
    if (this.confirmPassword!=='')
      this.passwordError = !(this.userinfo.password===this.confirmPassword);
    else
      this.passwordError = false;
  }

  checkPhonePattern(): boolean
  {
    let matcher = new RegExp('^[+ 0-9]{10}$');
    return (matcher.test(this.userinfo.phone));
  }

  passwordValidation()
  {
    if (this.userinfo.password.length==0) {
      this.pwdStrength = -1;
      return;
    }

    if (this.userinfo.password.length<8) {
      this.pwdStrength = 0;
      return;
    }

    const format1 = /[ !@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;
    const format2 = /[0-9]/;
    const format3 = /[a-z]/;
    const format4 = /[A-Z]/;
    var value1 = format1.test(this.userinfo.password);
    var value2 = format2.test(this.userinfo.password);
    var value3 = format3.test(this.userinfo.password);
    var value4 = format4.test(this.userinfo.password);

    if (value1 || (value2 && value3 && this.userinfo.password.length < 12))
      this.pwdStrength = 1;

    if (value1 && value2 && value4 && value3)
      this.pwdStrength = 2;

    if (this.userinfo.password.length>20)
      this.pwdStrength = 2;
  }

  registerUser() {

    this.emailError = !this.emailValidation();
    if (this.userinfo.firstname=='' || this.userinfo.firstname==null) {
      this.firstNameNotEntered = true;
    }
    if (this.userinfo.password=='' || this.userinfo.password==null || this.userinfo.password.length<8) {
      this.passwordNotEntered = true;
    }
    if (this.userinfo.phone=='' || this.userinfo.phone==null || this.userinfo.phone.length<10)
    {
      this.phoneNotEntered  = true;
      return;
    }

    if (this.invalidPhone)
      return;
    if (this.passwordError)
      return;

    if (this.pwdStrength<=0)
      return;

    let url = ip+"/register";
    this.http.post(url,this.userinfo).subscribe(
      res => {
        AppComponent.userinfo = this.userinfo;
        this.router.navigate(['login']);
        NavbarComponent.success="User Registered, Please login";
        NavbarComponent.checkAllMethods();
      },
      err => {
        alert("Server problem");
      }
    )

  }

  phoneCheck() {
    if (this.userinfo.phone.length == 10) {
      if (!this.checkPhonePattern()) {
        this.phoneError = false;
        this.invalidPhone = this.userinfo.phone !== '';
        return;
      }
      this.invalidPhone = false;
      let url = ip+"/phonecheck/" + this.userinfo.phone;
      this.http.get<boolean>(url).subscribe(
        res => {
          console.log(res);
          this.phoneError = res;
          if(!res)
          {
            //http://apilayer.net/api/validate?access_key=607ec08df9a49102801594d7fbb5bfe9&number=917975293566
            this.phoneDetails(this.userinfo.phone);
          }
        },
        err => {
          alert("server error")
        }
      );
    }
    else if(this.userinfo.phone.length==0) {
      this.invalidPhone = false;
      this.phoneVerify = null;
    }
  }

  phoneDetails(phone: string)
  {
    let url = "http://apilayer.net/api/validate?access_key=607ec08df9a49102801594d7fbb5bfe9&number=91"+phone;
    this.http.get<PhoneVerify>(url).subscribe(
      res => {
          this.phoneVerify = res;
          if (!this.phoneVerify.valid)
            this.invalidPhone=true;
      },
      err => {
        this.invalidPhone=true;
      }
    )
  }
}

export class PhoneVerify{
    valid: boolean;
    number: string;
    local_format: string;
    international_format: string;
    country_prefix: string;
    country_code: string;
    country_name: string;
    location: string;
    carrier: string;
    line_type: string;
}


