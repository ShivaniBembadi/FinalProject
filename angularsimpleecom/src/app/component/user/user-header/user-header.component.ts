import { Component, OnInit } from '@angular/core';
import { EcommerceService } from '../../../ecommerce.service';
import { NavigationStart, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-user-header',
  templateUrl: './user-header.component.html',
  styleUrl: './user-header.component.css'
})
export class UserHeaderComponent implements OnInit {
  url: string = "/user/home";
  userName: string = '';


  categories = [];
  selectedCategoryId: number | null = null;



  constructor(
    private bservice : EcommerceService,
    private router:Router
  ) {
    if (this.bservice.getUserName() !== null) {
      this.userName = this.bservice.getUserName();
    }
  }

  ngOnInit(): void {
    this.router.events.pipe(
      filter(event => event instanceof NavigationStart)
    ).subscribe((event: any) => {
      this.url = event?.url;
    });

  }
  routerToLink(link: string): void {
    if (link === '/user/logout') {
      this.bservice.userLogout();
      return;
    }
    this.router.navigate([link]);
  }
  

}

