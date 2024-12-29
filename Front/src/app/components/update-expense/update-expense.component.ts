import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ExpenseServiceService } from 'src/app/service/expense-service.service';

@Component({
  selector: 'app-update-expense',
  templateUrl: './update-expense.component.html',
  styleUrls: ['./update-expense.component.scss']
})
export class UpdateExpenseComponent {


  expenseForm! : FormGroup;
  allExpense: any[] = [];
  id:number = this.activeid.snapshot.params['id'];

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
              ,private router:Router
              ,private activeid:ActivatedRoute) {
  }
  
  ngOnInit() {
    this.expenseForm = this.fb.group({
      title: [null, [Validators.required]],
      amount : [null, [Validators.required]],
      date: [null, [Validators.required]],
      category : [null, [Validators.required]],
      description: [null, [Validators.required]]
    });
    this.showExpense();
  }

  showExpense() {
    this.expenseService.getExepenseByIdSrv(this.id).subscribe(res => {
      this.expenseForm.patchValue(res);
    },error =>{
      this.nzMessageService.error('Error while fetching Expense', { nzDuration: 2000 });
    });
  }

  editExpense() {
    // this.nzMessageService.info('Updating Expense', { nzDuration: 2000 });
    this.expenseService.editExpenseSrv(this.id,this.expenseForm.value).subscribe(res => {
      this.nzMessageService.success('Expense Updated Successfully', { nzDuration: 2000 });
      this.router.navigate(['/expense']);
    }, err => {
      this.nzMessageService.error('Error while updating Expense', { nzDuration: 2000 });
    });
  }
}
