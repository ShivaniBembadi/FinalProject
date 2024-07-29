import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EcommerceService } from '../../../ecommerce.service';
import { forkJoin, take } from 'rxjs';
import { Product } from '../../modal/product.modal';
import { Cart } from '../../modal/cart.modal';

@Component({
  selector: 'app-user-cart',
  templateUrl: './user-cart.component.html',
  styleUrl: './user-cart.component.css'
})
export class UserCartComponent implements OnInit {
  cartList: Cart[] = [];
  cartListBackup: Cart[] = [];
  grandTotal: number = 0;
  user: any = {};
 


  constructor(
    private bservice: EcommerceService,
    private router: Router,
    private datePipe: DatePipe
  ) {
    this.bservice.isUserLoginPresent();
    this.getCartList();
    this.getUserDetail();
  }

  ngOnInit(): void {
  }
  getCartList(): void {
    this.bservice.cartList().pipe(take(1)).subscribe(
      (res: any) => {
        console.log("****", res);
        if (!!res && Array.isArray(res)) {
          const userFilter = res;
          console.log("user filter::::::",userFilter);
          this.cartList = userFilter;
          //this.cartListBackup =  _.cloneDeep(userFilter);
          if (this.cartList.length > 0) {
            this.cartList.map((item: Cart) => {
              this.grandTotal += (item?.mrpPrice * item?.quantity);
            })
          }
        }
      }, err => {
        console.log("error");
      }

    );
  }
  getTotal(quantity: number = 0, mrpPrice: number = 0): number {
    return quantity * mrpPrice;
  }
   placeOrder(): void {
    let totalPrice: number = 0;
    const deleteCartReq:any[]=[];
    const productItems: Array<Product> = [];
    this.cartList.forEach((item: Cart) => {
      productItems.push(item?.product);
      totalPrice += (item?.mrpPrice * item?.quantity);
      deleteCartReq.push(this.bservice.deleteCart(item?.id));
    });
    console.log('>>>>>>>>', totalPrice)
    const body: any = {
      totalPrice: totalPrice,
      orderStatus: "success",
      paymentStatus: "success",
       orderedDate: this.datePipe.transform(new Date(), 'yyyy-MM-dd'),
       user: this.user,
       product: productItems,
       
    };
    this.bservice.placeOrderItem(this.user?.userId, body).pipe(take(1)).subscribe((res: any) => {
      console.log('>>>>>>>', res);
     forkJoin(deleteCartReq).pipe(take(1)).subscribe();
      alert("Place order Sucessfully");
      this.router.navigate(["/user/order"]);
    })


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

  deleteCart(cart:Cart, showAlert: boolean = true):void{
    this.bservice.deleteCart(cart.id).pipe(take(1)).subscribe(
      (res: any) => {
        if (showAlert) {
          alert("Product deleted sucessfully");
        }
       
        this.getCartList();
      }, err => {
        console.log("Err");
      }
    )
  }

  onIncreaseQunatity(cart: Cart): void {
    const index = this.cartList.findIndex((item: Cart) => item.id === cart?.id);
    // const bac = Object.assign(this.cartListBackup);
    const indexBackup = this.cartListBackup.findIndex((item: Cart) => item.id === cart?.id);
    const qty = cart.quantity + 1;
    console.log( this.cartListBackup[indexBackup].quantity , '>>>>>>' , (cart.product?.quantity ))
    if (qty > (cart.product?.quantity  + this.cartListBackup[indexBackup].quantity) ) {
      alert('Added quantity should not greater than avaiable quantity');
      return;
    }
    this.cartList[index].quantity = qty;
    this.updateGrantTotal();
  }

  onDecreaseQunatity(cart: Cart): void {
    const index = this.cartList.findIndex((item: Cart) => item.id === cart?.id);
    const qty = cart.quantity - 1;
    if (qty === 0) {
      this.deleteCart(cart, false);
    }
    this.cartList[index].quantity = qty;
    this.updateGrantTotal();
  }

  updateGrantTotal(): void {
    let total = 0;
    this.cartList.map((item: Cart) => {
      total+= (item?.mrpPrice * item?.quantity);
     
    })
    this.grandTotal = total;
  }


}


