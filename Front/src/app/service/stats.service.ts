import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StatsService {

  private BaseUrl = 'http://localhost:8080/';
  constructor(private http:HttpClient) { }

  getStatsSrv() {
    return this.http.get(this.BaseUrl + 'api/graph/stats');
  }

  getChartSrv() {
    return this.http.get(this.BaseUrl + 'api/graph/data');
  }



}
