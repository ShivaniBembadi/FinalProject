import { ProductCategory } from "./productCategory.modal";
import { ProductSubCategory } from "./productSubCategory.modal";

export interface Product{
    productId: number;
    name: string;
    description : string;
    price : number;
    quantity :number;
    image: string;
    //mrpPrice: number;
    category: ProductCategory;
    subCategory: ProductSubCategory;
}
