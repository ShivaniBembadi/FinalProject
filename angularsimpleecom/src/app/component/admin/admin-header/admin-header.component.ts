import { ChangeDetectorRef, Component } from '@angular/core';
import { EcommerceService } from '../../../ecommerce.service';
import { NavigationStart, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrl: './admin-header.component.css'
})
export class AdminHeaderComponent {
  url: string = '';
userName: string = '';

constructor(
  private route:Router,
  private eservice:EcommerceService,
  private changeDetector:ChangeDetectorRef
  ){
    if (this.eservice.getUserName() !== null) {
      this.userName = this.eservice.getUserName();
    }
  }

  ngOnInit(): void {
    this.route.events.pipe(
      filter(event => event instanceof NavigationStart)
    ).subscribe((event: any) => {
      this.url = event?.url;
    });

  }  
  gotourl(link: string): void {
    if (link === '/admin/logout') {
      this.eservice.userLogout();
      return;
    }
    this.route.navigate([link]);

}

}
