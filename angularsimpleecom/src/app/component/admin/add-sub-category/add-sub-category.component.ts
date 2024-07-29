import { Component } from '@angular/core';
import { take } from 'rxjs';
import { EcommerceService } from '../../../ecommerce.service';
import { ProductCategory } from '../../modal/productCategory.modal';

@Component({
  selector: 'app-add-sub-category',
  templateUrl: './add-sub-category.component.html',
  styleUrl: './add-sub-category.component.css'
})
export class AddSubCategoryComponent {
  categoryName: string = '';
  allCategory: Array<ProductCategory> = [];
  parentCategory: number = 0;
  constructor(
    private service: EcommerceService
  ) {
    this.service.getAllCategoryList().pipe(take(1)).subscribe((res: any) => {
      if (res && res.length > 0) {
        this.allCategory = res;
        this.parentCategory = this.allCategory[0].categoryId;
        console.log('###', this.allCategory)
      }
    });
  }

  addSubCategory(): void {
    if (this.categoryName === '') {
      alert("Please add Sub category");
      return;
    }
    console.log('>>>>>', this.parentCategory)
   
    const body = {
      subCategoryName: this.categoryName,
      category: {
        "categoryId": this.parentCategory
      }
    };
    this.service.addSubCategory(body).pipe(take(1)).subscribe((res: any) => {
      if (res) {
        alert('Sub Category Added Successfully');
        this.service.navigateToLink('/admin/home');
      }
      console.log('>>>>>');
    });
  }
}

