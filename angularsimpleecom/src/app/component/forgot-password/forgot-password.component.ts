import { Component } from '@angular/core';
import { EcommerceService } from '../../ecommerce.service';
import { Router } from '@angular/router';
import { take } from 'rxjs';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.css'
})
export class ForgotPasswordComponent {
  emailId: string= '';
  isShowChangePassword: boolean = false;
  newPassword: string = '';
  user: any;

  constructor(
    private bservice: EcommerceService,
    private route: Router
  ) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
   
    this.bservice.findUserByEmail(this.emailId).pipe(take(1)).subscribe((res) => {
      if (!!res && res?.id) {
        this.user = res;
        this.isShowChangePassword = true;
      }
    }, err => {
      this.isShowChangePassword = false;
      alert("User not found . Please enter valid email.")
    });
  }

  onChangePassword(): void {
    // this.user.passw = this.newPassword;
    this.bservice.changeUserPassword(this.user?.id, this.newPassword).pipe(take(1)).subscribe((res) => {
      if (res && res.id) {
        alert("Password changed successfully");
        this.route.navigate(["/user-login"]);
      }
    }, error => {
      alert("Error occured while changing password.");
    });
  }
}

