import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {






  gotoLogin() :void{
    this.route.navigate(['/user-login']);
  
  }
  
  constructor( private route: Router) {
    
  }
  ngOnInit(): void {
    
  }  
}