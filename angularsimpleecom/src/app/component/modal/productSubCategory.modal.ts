import { ProductCategory } from "./productCategory.modal";

export interface ProductSubCategory{
    subCategoryId: number;
    subCategoryName: string;
    category:ProductCategory
}