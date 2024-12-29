import { Component, ElementRef, ViewChild } from '@angular/core';
import { StatsService } from 'src/app/service/stats.service';
import Chart from 'chart.js/auto';
import { CategoryScale } from 'chart.js/auto';

Chart.register(CategoryScale);


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

  stats:any;
  stats2:any;
  expenses:any[] = [];
  incomes:any[] = [];

  @ViewChild('incomeChart') private IncomechartRef:ElementRef;
  @ViewChild('expenseChart') private expensechartRef:ElementRef;

  constructor(private statsSrv:StatsService) { }

  createLineChart() {
    const ctx = this.IncomechartRef.nativeElement.getContext('2d');
    const ctx2 = this.expensechartRef.nativeElement.getContext('2d');

    new Chart(ctx, {
      type: 'line',
      data: {
        labels: this.incomes.map((income) => income.date),
        datasets: [{
          label: 'Income  ',
          data: this.incomes.map((income) => income.amount),
          borderWidth: 1,
          backgroundColor: 'rgb(80,200,120)',
          borderColor: 'rgba(0,100,0)',
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
    new Chart(ctx2, {
      type: 'line',
      data: {
        labels: this.expenses.map((expenses) => expenses.date),
        datasets: [{
          label: 'Expense  ',
          data: this.expenses.map((expense) => expense.amount),
          borderWidth: 1,
          backgroundColor: 'rgb(80,200,120)',
          borderColor: 'rgba(0,100,0)',
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }

  gridStyle = {
    width: '25%',
    textAlign: 'center',
  }

  ngOnInit() {
    this.getStats();
    this.getChart();
  }

  getStats() {
    this.statsSrv.getStatsSrv().subscribe(res => {
      console.log(res);
      this.stats = res;
    }, err => {
      console.log(err);
    });
  }

  getChart() {
    this.statsSrv.getChartSrv().subscribe(res => {
      // Ensure incomesList exists and is an array
        if(res){
          console.log("===========================");
          console.log(res);
          console.log("===========================");
            this.stats2 = res;
            this.expenses = this.stats2.expensesList;
            this.incomes = this.stats2.incomesList;
            console.log(this.expenses);
            console.log(this.incomes);
            this.createLineChart();
        }
    }, err => {
      console.log(err);
    });
  }
}
