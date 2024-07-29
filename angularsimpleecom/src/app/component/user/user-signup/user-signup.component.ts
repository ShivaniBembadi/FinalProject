import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EcommerceService } from '../../../ecommerce.service';
import { take } from 'rxjs';

@Component({
  selector: 'app-user-signup',
  templateUrl: './user-signup.component.html',
  styleUrl: './user-signup.component.css'
})
export class UserSignupComponent {
  firstName: string = ""
  lastName: string = "";
  email: string = "";
  password: string = "";
 // dob: string = "";
  phone: string = "";
  district: string = "";
  state: string = "";
  zipcode: string = "";
  gender: string = "Female";
  //address: string = "";
  plotNo:string=" ";
  streetName:string=" ";
  city:string=" ";

  constructor(
    
    private router: Router,
    private datePipe: DatePipe,
    private bservice: EcommerceService

  ) { }

  /*ngOnInit(): void {
  }
  setDOB(ev: any): void {
    const date: any = this.datePipe.transform(ev?.value, 'yyyy-MM-dd');
    this.dob = date;
  }
*/

  signup(): void {
    if (this.firstName === '' || this.firstName.length < 3) {
      alert('FirstName must contain atleast 3 characters');
      return;
    }
    if (this.lastName === '' || this.lastName.length < 3) {
      alert('LastName must contain atleast 3 characters');
      return;
    }

    if (this.phone === '' || this.phone.length < 10 || this.phone.length > 10) {
      alert('Phone must contain atleast 10 characters');
      return;
    }
    const pattern=/^[6789][0-9]{9}$/;
    if (!pattern.test(this.phone)) {
      alert('Invalid mobile number.');
      return;
    }

    const body: any = {
      firstName : this.firstName,
      lastName : this.lastName,
      gender :this.gender,
      //dob :this.dob,
      emailId:this.email,
      userName: this.firstName + ' ' + this.lastName,
      password:this.password,
      role: "client",
      //role: "admin",
      phoneNumber : this.phone,
      //address: this.address,
      plotNo:this.plotNo,
      streetName:this.streetName,
      city:this.city,
      district:this.district,
      state:this.state,
      zipCode:this.zipcode
    }
    console.log("=======>",body);
    this.bservice.signUp(body).pipe(take(1)).subscribe((res :any) => {
      console.log("*****",res);
      if(res && res?.userId){
        alert("Registration sucessful");
        this.router.navigate(["/register"]);
      }
    }, err =>{
        console.log("Error  ", err);
        if (err && err?.error === 'Oops duplicate Entry of the data !') {
          alert("Email address registered already, please go to login.");
        } else {
          alert("Something going wrong..pls try again");
        }
    })

  }




}
