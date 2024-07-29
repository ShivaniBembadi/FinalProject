import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EcommerceService {
  url: string = 'http://localhost:8080';
  

  constructor(
    private http:HttpClient,
    private route:Router
  ) { }
   

  /* Client Registration */
  signUp(body: any): Observable<any> {
    return this.http.post(this.url + "/api/users/register", body);
  }
  //client login
  userSignIn(body: any): Observable<any> {
    return this.http.post(this.url + "/api/users/login", body);
  }
  //once we logged in that time we are storing client id into token 
  storeUserAuthorization(token: string): void {
    localStorage.setItem("token", token);
  }

  getUserAuthorization(): any {
    if (typeof localStorage !== 'undefined') {
      const token = localStorage.getItem("token");
      return token;
    } else {
      return null;
    }
  }

  storeUserName(name: string): void {
    localStorage.setItem("userName", name);
  }

  getUserName(): any {
    if (typeof localStorage !== 'undefined') {
      const name = localStorage.getItem("userName");
      return name;
    }
    return null;
  }

  userLogout(): void {
    localStorage.clear();
    this.route.navigate(['']);
  }
  //admin login
  adminSignIn(body: any): Observable<any> {
    return this.http.post(this.url + "/api/users/login", body);
  }
  storeAdminAuthorization(token: string): void {
    localStorage.setItem("admin", token);
  }
  getAdminAuthorization(): any {
    const token = localStorage.getItem("admin");
    return token;
  }

  storeAdminUserName(name: string): void {
    localStorage.setItem("adminName", name);
  }

  getAdminName(): any {
    const name = localStorage.getItem("adminName");
    return name;
  }

  adminLogout(): void {
    localStorage.clear();
    this.route.navigate(['/']);
  }

  // this is to get username in admin.home.html part via admin.home.ts
  isAdminLoginPresent(): void {
    if (this.getAdminAuthorization() === null) {
      this.route.navigate(['/admin-login']);
    }
  }

  isUserLoginPresent(): void {
    if (this.getUserAuthorization() === null) {
      this.route.navigate(['/user-login']);
    }
  }

  deleteProduct(id :any):Observable<any> {
     return this.http.delete(this.url + "/admin/deleteproduct/" +id);
  }
  
  getProductById(id:any):Observable<any> {
    return this.http.get(this.url + "/admin/productbyid/"+id);
  }
  
  editProduct(body: any,id:any): Observable<any> {
    return this.http.put(this.url + "/admin/updateproduct/"+id, body);
  }

  getProductSearch(keyword:any):Observable<any> {
    return this.http.get(this.url + "/admin/search?name="+keyword);
  }
  



  addToCart(body: any,pid:any,cid:any):Observable<any>{
    return this.http.post(this.url+"/cartitem/add/"+cid+"/"+pid,body,  {responseType: 'text'});
  }
  
  getUserById(id:any):Observable<any> {
    return this.http.get(this.url + "/api/users/"+id);
  }
  
  cartList():Observable<any>{
    const id = localStorage.getItem("token");
    return this.http.get(this.url+"/cartitem/user/"+id);
  }
  placeOrder(cid:any,cartid:any,body:any):Observable<any> {
    return this.http.post(this.url + "/api/orders/"+cid+"/"+cartid, body);
  }
  deleteCart(id :any):Observable<any> {
    return this.http.delete(`${this.url}/cartitem/delete/${id}`,  {responseType: 'text'});
  }
  
  orderList(id:any):Observable<any>{
    return this.http.get(this.url+"/api/orders/"+id);
  }
  
  addPayment(body:any,orderid:any,cid:any):Observable<any> {
    return this.http.post(this.url + "/api/payements/"+orderid+"/"+cid, body);
  }

  forgotPassword(body: any):Observable<any> {
    return this.http.post(this.url + "/api/users/forgotpassword", body);
  }
  
  updateUserInformation(body: any):Observable<any> {
    return this.http.put(this.url + "/api/users/updateuser/"+body?.userId, body);
  }
  
  changePassword(uid: any,password:any):Observable<any> {
    return this.http.post(this.url + "/api/users/changepassword"+uid+"/"+password,{});
  }

  getAllorderList():Observable<any>{
    return this.http.get(this.url+"/api/orders");
  }

  placeOrderItem(cid:any, body:any):Observable<any>{
    return this.http.post(this.url + "/api/orders/addOrderItem/"+cid, body);
  }

  addPaymentOfOrder(amount: any):Observable<any> {
    return this.http.get(this.url + "/api/orders/createTransaction/"+amount);
  }

  storeUserRole(role: string): void {
    localStorage.setItem("role", role);
  }

  getUserRole(): any {
    const role = localStorage.getItem("role");
    return role;
  }

  getAllProducts():Observable<any> {
    return this.http.get(this.url + "/admin/allproducts");
  }

  getAllSubCategoryList():Observable<any> {
    return this.http.get(this.url + "/subcatagory/list");
  }

  getAllCategoryList():Observable<any> {
    return this.http.get(this.url + "/catagory/list");
  }

  addProduct(body: any):Observable<any>{
    return this.http.post(this.url + "/admin/addproduct", body, {});
  }

  addCategory(body: any):Observable<any>{
    return this.http.post(this.url + "/catagory/add", body, {});
  }

  addSubCategory(body: any):Observable<any>{
    return this.http.post(this.url + "/subcatagory/add", body, {});
  }

  findUserByEmail(email: string):Observable<any> {
    return this.http.get(this.url + "/api/user/findByUserEmail/"+email);
  }

  changeUserPassword(uid: string, password: string):Observable<any> {
    return this.http.post(this.url + "/api/users/changepassword/"+uid+"/"+password, {});
  }

  
  navigateToLink(path: string): void {
    this.route.navigate([path]);
  }

}



  
