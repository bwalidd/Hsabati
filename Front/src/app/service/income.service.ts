import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class IncomeService {

  private BaseUrl = 'http://localhost:8080/';
  constructor(private http:HttpClient) { }

  postIncomeSrv(IncomeDto:any) {
    return this.http.post(this.BaseUrl + 'api/income', IncomeDto);
  }

  getAllIncomeSrv() {
    return this.http.get(this.BaseUrl + 'api/income/all');
  }

  DeleteIncomeSrv(id:any) {
    return this.http.delete(this.BaseUrl + 'api/income/delete/' + id);
  }

  getIncomeByIdSrv(id:number) {
    return this.http.get(this.BaseUrl + 'api/income/' + id);
  }

  editIncomeSrv(id:number, IncomeDto:any) {
    return this.http.put(this.BaseUrl + 'api/income/edit/' + id, IncomeDto);
  }

  editExpenseSrv(id:number, ExpenseDto:any) {
    return this.http.put(this.BaseUrl + 'api/expense/edit/' + id, ExpenseDto);
  }

}
