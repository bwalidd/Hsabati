import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ExpenseServiceService } from 'src/app/service/expense-service.service';
import { IncomeService } from 'src/app/service/income.service';

@Component({
  selector: 'app-update-income',
  templateUrl: './update-income.component.html',
  styleUrls: ['./update-income.component.scss']
})
export class UpdateIncomeComponent {

  incomeForm! : FormGroup;
  allExpense: any[] = [];
  id:number = this.activeid.snapshot.params['id'];

  listOfCategory : any[] = [
    "Youtube",
    "GoogleAdsense",
    "Shopify",
    "AmazonFba",
    "Freelancing",
    "Others"
  ]

  constructor(private fb:FormBuilder,
              private nzMessageService:NzMessageService,
             private incomeService:IncomeService
              ,private router:Router
              ,private activeid:ActivatedRoute) {
  }
  
  ngOnInit() {
    this.incomeForm = this.fb.group({
      title: [null, [Validators.required]],
      amount : [null, [Validators.required]],
      date: [null, [Validators.required]],
      category : [null, [Validators.required]],
      description: [null, [Validators.required]]
    });
    this.showIncome();
  }

  showIncome() {
    this.incomeService.getIncomeByIdSrv(this.id).subscribe(res => {
      this.incomeForm.patchValue(res);
    },error =>{
      this.nzMessageService.error('Error while fetching Expense', { nzDuration: 2000 });
    });
  }

  editIncome() {
    // this.nzMessageService.info('Updating Expense', { nzDuration: 2000 });
    this.incomeService.editExpenseSrv(this.id,this.incomeForm.value).subscribe(res => {
      this.nzMessageService.success('Expense Updated Successfully', { nzDuration: 2000 });
      this.router.navigate(['/expense']);
    }, err => {
      this.nzMessageService.error('Error while updating Expense', { nzDuration: 2000 });
    });
  }
}
