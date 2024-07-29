import { Component } from '@angular/core';
import { EcommerceService } from '../../../ecommerce.service';
import { take } from 'rxjs';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrl: './add-category.component.css'
})
export class AddCategoryComponent {
  categoryName: string = '';
  constructor(
    private service: EcommerceService
  ) {}

  addCategory(): void {
    if (this.categoryName === '') {
      alert("Please add category");
      return;
    }
    const body = {
      categoryName: this.categoryName
    };
    this.service.addCategory(body).pipe(take(1)).subscribe((res) => {
      if (res) {
        alert('Category Added Successfully');
        this.service.navigateToLink('/admin/home');
      }
      console.log('>>>>>');
    });
  }
}


