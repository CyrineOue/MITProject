import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from 'src/app/model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  productURL = "api/mit/product/retrieve-all-products";
  constructor(private http : HttpClient) { }
  
//##############################################Crud##############################################""

  getProducts() : Observable<Product[]>{
    return this.http.get<Product[]>(this.productURL);
  }

  deleteProductById(id:number){
    return this.http.delete<Product>("api/mit/product/remove-product/"+id);
  }

  editProduct(product: Product){
    return this.http.put<Product>("api/mit/product/update-product",product);
  }

  addProduct(product:Product){
    return this.http.post<Product>("api/mit/product/add-product",product);
    }
//##################################Simulation######################################

  simulAHI(fieldvalue:number,location:string,fieldsize:number){
    return this.http.get<number>("api/mit/product/ahipremuimcalculation/"+fieldvalue+"/"+location+"/"+fieldsize)
  }

  simulLSP(livestocknumber:number,livestockvalue:number,typels:string){
    return this.http.get<number>("api/mit/product/almipremuimcalculation/"+livestocknumber+"/"+livestockvalue+"/"+typels)
  }

  simulSBFI(propertyvalue:number,sizeproperty:number){
    return this.http.get<number>("api/mit/product/sbfipremuimcalculation/"+propertyvalue+"/"+sizeproperty)
  }

  simulBM(propertyvalue:number,insuredage:number){
    return this.http.get<number>("api/mit/product/bmipremuimcalculation/"+propertyvalue+"/"+insuredage)
  }
  //########################################SMS##############################################

  SendSMS(productid:number){
    return this.http.post("api/mit/api/v1/sms/"+productid,null);

  }
  //#########################################Stat#######################################################
  Count() : Observable<number>{
    return this.http.get<number>("api/mit/product/count-product");
  }

  Best() : Observable<Product>{
    return this.http.get<Product>("api/mit/product/best-product");
  }

  Worst() : Observable<Product>{
    return this.http.get<Product>("api/mit/product/worst-product");
  }
}
