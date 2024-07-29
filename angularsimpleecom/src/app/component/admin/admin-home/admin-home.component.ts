import { Component, OnInit } from '@angular/core';
import { EcommerceService } from '../../../ecommerce.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrl: './admin-home.component.css'
})
export class AdminHomeComponent implements OnInit {
  userName: string = '';
  constructor(
    private bservice: EcommerceService
  ) {
    if (this.bservice.getUserName() !== null) {
      this.userName = this.bservice.getUserName();
      console.log("*******",this.userName);
    }
    this.bservice.isUserLoginPresent();
  }

  ngOnInit(): void {
  }
}
