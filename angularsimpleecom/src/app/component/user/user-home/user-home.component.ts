import { Component, OnInit } from '@angular/core';
import { EcommerceService } from '../../../ecommerce.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { debounceTime, distinctUntilChanged, Subject, take } from 'rxjs';
import { Product } from '../../modal/product.modal';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrl: './user-home.component.css'
})
export class UserHomeComponent implements OnInit{
  productList: Array<Product> = [];
  quantity: number = 0;
  user: any = {};
  getCategoryList: any[] = [];
  category: any = 100;
  allProductList : Array<Product>= [];
  offset: number = 0;
  pageSize: number = 5; // How many item you want to display in your page.
  totalbook: number = 1;
  searchType: string = "bycategory";
  searchKeyword: string = "";
  userInputUpdate = new Subject<string>();

  constructor(
    private bservice: EcommerceService,
    private router: Router,
    private snakcbar: MatSnackBar
  ) {
   this.bservice.isUserLoginPresent();
    this.getProductList(true);
    this.getUserDetail();

  }


  ngOnInit(): void {
    this.bservice.getAllCategoryList().pipe(take(1)).subscribe((res: any) => {
      if (res) {
        console.log('>>>>>>>>', res);
        this.getCategoryList = res;
      }
    });
    this.userInputUpdate.pipe(
      debounceTime(400),
      distinctUntilChanged())
      .subscribe(value => {
        if (value.length > 0) {

          this.bservice.getProductSearch(value).pipe((take(1))).subscribe((res: any) => {
            console.log('$$$$$$$$', res);
            if (res) {
                this.productList = res;
                this.allProductList = res;
            }
          });
        } else {
          this.productList = [];
        }
        
      });
  }

  getUserDetail(): void {
    const cid = this.bservice.getUserAuthorization();
    this.bservice.getUserById(cid).pipe(take(1)).subscribe(
      (res: any) => {
        console.log("User***", res);
        if (!!res && res?.userId) {
          this.user = res;
        }
      }, err => {
        console.log("Err");
      }
    )
  }


  getProductList(isAllBook: boolean = false): void {
    this.bservice.getAllProducts().pipe(take(1)).subscribe((res: any) => {
      if (res && Array.isArray(res)) {
        this.productList = res;
        this.allProductList = res;
        console.log('^^^^^', this.productList)
      }
    }, (err: any) => {
      console.log("Error");
    });
  }

  addToCart(product: Product): void {
  const element: any = document.getElementById(product?.productId.toString());
  let qty:any= element!==null ? element.value : 0; 
  if(qty ===""){
    element.value=0;
    qty=0;
  }
    if (qty === 0 || qty === "0" || qty <0) {
      alert("Qunatity must be more than zero");
      return ;
    }

    if (qty > product?.quantity) {
      alert('Added quantity should not greater than available quantity');
      return;
    }
    
    const body: any = {
      quantity: qty,
      mrpPrice: product?.price,
      user: this.user,
      product: product
    };
    console.log("add to cart:: ", body);
    this.bservice.addToCart(body, product?.productId, this.user?.userId).pipe(take(1)).subscribe(
      (res: any) => {
        console.log(res);
       
        alert("Added to cart sucessfully");
          this.getProductList(true);
      }, err => {
        console.log("Error");
      }
      
    )
    
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
  gotocartList(): void {
    this.router.navigate(["/user/cart"]);
  }
  
  getSelectedType(event: any): void {
    this.searchType = event?.value;
    if (this.searchType === "bysearch") {
      this.productList = [];
    } else {
      //All category dropdown
      this.getProductList(true);
    }
  }

  getSearchWord(ev: any): void {
    setTimeout(() => {
      this.userInputUpdate.next(this.searchKeyword);
    }, 100);
  }

}

