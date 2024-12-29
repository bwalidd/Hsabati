import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ExpenseServiceService } from 'src/app/service/expense-service.service';


@Component({
  selector: 'app-expense',
  templateUrl: './expense.component.html',
  styleUrls: ['./expense.component.scss'],
})

export class ExpenseComponent {

  expenseForm! : FormGroup;
  allExpense: any[] = [];

  listOfCategory : any[] = [
    "Education",
    "Groceries",
    "Health",
    "Subscription",
    "Takeaways",
    "Clothing",
    "Travelling",
    "Others"
  ]

  constructor(private fb:FormBuilder,
              private nzMessageService:NzMessageService,
              private expenseService:ExpenseServiceService
              ,private router:Router) {
  }
  
  ngOnInit() {
    this.getExpense();
    this.expenseForm = this.fb.group({
      title: [null, [Validators.required]],
      amount : [null, [Validators.required]],
      date: [null, [Validators.required]],
      category : [null, [Validators.required]],
      description: [null, [Validators.required]]
    });
  }


  postExpense() {
    this.expenseService.postExpenseSrv(this.expenseForm.value).subscribe(res => {
      this.nzMessageService.success('Expense Added Successfully', { nzDuration: 2000 });
    }, err => {
      this.nzMessageService.error('Error while adding Expense', { nzDuration: 2000 });
    });
  }


  getExpense() {
    this.expenseService.getExpenseSrv().subscribe(res => {
      this.allExpense = res;
      console.log("-------------------------------------");
      console.log(this.allExpense);
      console.log("-------------------------------------");
    }, err => {
      this.nzMessageService.error('Error while fetching Expense', { nzDuration: 2000 });
    });
  }

  updateExpense(id:number) {
    const url = `/expense/${id}/edit`;
    this.router.navigateByUrl(url);
  }

  deleteExpense(id:any) {
    this.expenseService.DeleteExpenseSrv(id).subscribe(res => {
      this.nzMessageService.success('Expense Deleted Successfully', { nzDuration: 2000 });
      this.getExpense();
    }, err => {
      this.nzMessageService.error('Error while deleting Expense', { nzDuration: 2000 });
    });
  }
}
