import { Component } from '@angular/core';
import { Product } from '../../modal/product.modal';
import { EcommerceService } from '../../../ecommerce.service';
import { Router } from '@angular/router';
import { take } from 'rxjs';

@Component({
  selector: 'app-admin-product-list',
  templateUrl: './admin-product-list.component.html',
  styleUrl: './admin-product-list.component.css'
})
export class AdminProductListComponent {
  productList: Array<Product> = [];
  getCategoryList: any[] = [];
  category: any = 100;
  allProductList: Array<Product> = [];
  offset: number = 0;
  pageSize: number = 5; // How many item you want to display in your page.
  totalbook: number = 1;

  constructor(
    private bservice: EcommerceService,
    private router: Router
  ) {
    this.bservice.isUserLoginPresent();
    this.getProductList(true);
  }

  ngOnInit(): void {
   
  }

  getProductList(isAllBook: boolean = false): void {
    this.bservice.getAllProducts().pipe(take(1)).subscribe((res: any) => {
      if (res  && Array.isArray(res)) {
        this.productList = res;
        this.allProductList = res;
      }
    }, (err: any) => {
      console.log("Error");
    });
  }

  delProduct(product: Product): void {
    this.bservice.deleteProduct(product?.productId).pipe(take(1)).subscribe(
      (res: any) => {
        alert("Product deleted sucessfully");
        this.getProductList(this.category === 100 || this.category === "100");
      }, err => {
        console.log("Error");
      }
    )
  }

  editProduct(product: Product): void {
    this.router.navigate(['/admin/add-product'], {
      queryParams: {
        id: product?.productId
      }
    });

  }

  getBookByCategory(): void {
    this.offset = 0;
    this.totalbook = 1;
    if (this.category === "100") {
      this.getProductList(true);
    } else {
      this.getProductList(false);
    }
  }

  onNextPageClick(pageOffSet: any): void {
    this.offset = pageOffSet;
    this.getProductList(this.category === 100 || this.category === "100");
  }

  onPreviousPageClick(pageOffSet: any): void {
    this.offset -= 1;
    this.getProductList(this.category === 100 || this.category === "100");
  }

  onFirstPageClick(pageOffSet: any): void {
    this.offset = 0;
    this.getProductList(this.category === 100 || this.category === "100");
  }

  onLastPageClick(pageOffSet: any): void {
    const lastPage = Math.ceil(this.totalbook / this.pageSize);
    this.offset = lastPage;
    this.getProductList(this.category === 100 || this.category === "100");
  }
}

