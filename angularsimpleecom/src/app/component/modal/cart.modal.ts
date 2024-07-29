import { Product } from "./product.modal";

export interface Cart{
    id : number;
    mrpPrice : number;
    quantity : number;
    user : any;
    product: Product
}