import { ProductSubCategory } from "./productSubCategory.modal";

export interface ProductCategory{
    categoryId: number;
    categoryName: string;
    subCategories: Array<ProductSubCategory>;
}