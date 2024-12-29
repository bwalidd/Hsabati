import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class ExpenseServiceService {
  
  private BaseUrl = 'http://localhost:8080/';

  constructor(private http:HttpClient) { }

  postExpenseSrv(ExpenseDto:any):Observable<any> {
    return this.http.post(this.BaseUrl + 'api/expense', ExpenseDto);
  }

  getExpenseSrv():Observable<any> {
    return this.http.get(this.BaseUrl + 'api/expense/all');
  }

  DeleteExpenseSrv(id:any):Observable<any> {
    return this.http.delete(this.BaseUrl + 'api/expense/' + id);
  }

  getExepenseByIdSrv(id:number):Observable<any> {
    return this.http.get(this.BaseUrl + 'api/expense/' + id);
  }

  editExpenseSrv(id:number, ExpenseDto:any):Observable<any> {
    return this.http.put(this.BaseUrl + 'api/expense/edit/' + id, ExpenseDto);
  }
}
