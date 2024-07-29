import { Component } from '@angular/core';
import { ProductCategory } from '../../modal/productCategory.modal';
import { take } from 'rxjs';
import { ProductSubCategory } from '../../modal/productSubCategory.modal';
import { EcommerceService } from '../../../ecommerce.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent {
  pname: string = '';
  pImage: string = '';
  pDescription: string = '';
  mrpPrice: number = 0;
  isEdit: boolean = false;
  //pPrice: number = 0;
  pQuantity: number = 0;
  pid: number = 0;
  catgoryList: Array<ProductCategory> = [];
  subCategoryList: Array<ProductSubCategory> = [];
  categoryId: number = 0;
  subCategoryId: number = 0;

  constructor(

    private bservice: EcommerceService,
    private router: Router,
    private activateRouter: ActivatedRoute
  ) {
    this.activateRouter.queryParams.subscribe((params: any) => {
      if (params?.id) {
        this.isEdit = true;
        this.bservice.getProductById(params?.id).pipe(take(1)).subscribe((res:any)=> {
          if(!!res && res){
            this.getCategoryList();
            setTimeout(() => {
              this.pid = res?.productId;
              this.pname = res?.name;
              this.pImage = res?.image;
              this.pDescription = res?.description;
              this.mrpPrice = res?.price;
              //this.pPrice = res?.pPrice;
              this.pQuantity = res?.quantity;
              this.categoryId = res?.productCategory?.category_id;
              this.subCategoryId = res?.subCategory?.sub_catagory_id;
              this.getSubList();
            }, 100);
           
          }
          console.log(res);
        });
      }

    })
  }
  ngOnInit(): void {
    this.bservice.isUserLoginPresent();
    this.getCategoryList();
  }

  getCategoryList(): void {
      this.bservice.getAllCategoryList()
        .pipe(take(1))
        .subscribe((res: any) => {
          if (res && Array.isArray(res)) {
            this.catgoryList = res;
          }
        });
  }

  onAddProduct(): void {
   
    if (this.pname === '') {
      alert("Product Nme is required");
      return;
    }
    if (this.pDescription === '') {
      alert("description  is required");
      return;
    }

    if (this.pImage === '') {
      alert("Image should not be blank");
      return;
    }
    console.log("******MRP price",this.mrpPrice);
    if (this.mrpPrice === 0 || this.mrpPrice===null) {
     alert("MRP Price should not be zero/blank");
      return;
    }
    if (this.pQuantity === 0|| this.pQuantity===null || this.pQuantity <0) {
      alert("Quantity should not be zero/blank and negative");
      return;
    }
    
 

    const body: any = {
      name: this.pname,
      image: this.pImage,
      description: this.pDescription,
      price: this.mrpPrice,
      quantity: this.pQuantity,
      category: {categoryId: this.categoryId},
      subCategory: {subCategoryId: this.subCategoryId}
    };
    console.log('>>>', body)
    
    if(this.isEdit){
      console.log("=======>", body);
    this.bservice.editProduct(body,this.pid).pipe(take(1)).subscribe((res: any) => {
      console.log("*****", res);
      if (res && res?.productId) {
        alert("Product updated sucessfully");
        this.router.navigate(["/admin/productlist"]);
      }
    }, err => {
      console.log("Error  ", err);
      alert("Something going wrong!! Please try again");
    })
    }else{
      console.log("=======>", body);
      this.bservice.addProduct(body).pipe(take(1)).subscribe((res: any) => {
        console.log("*****", res);
        if (res) {
          alert("Product added sucessfully");
          this.router.navigate(["/admin/home"]);
        }
      }, err => {
        console.log("Error  ", err);
        alert("Something going wrong!! Please try again");
      })
    }
  }

  getSubList(): void {
    console.log('>>>>', this.catgoryList)
    console.log('>>>>>>>>', this.catgoryList)
    if (this.categoryId) {
      console.log('>xx>>', this.catgoryList.filter((item: ProductCategory) => parseInt(item?.categoryId.toString(), 10) === parseInt(this.categoryId.toString(), 10)));
      const subList = this.catgoryList.filter((item: ProductCategory) => parseInt(item?.categoryId.toString(), 10) === parseInt(this.categoryId.toString(), 10));
      console.log('>>>>>', subList);
      if (subList ) {
        this.subCategoryList = subList[0].subCategories;
      }
    }
  }

}
