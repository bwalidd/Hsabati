import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { IncomeService } from 'src/app/service/income.service';

@Component({
  selector: 'app-income',
  templateUrl: './income.component.html',
  styleUrls: ['./income.component.scss']
})
export class IncomeComponent {

  incomeForm! : FormGroup;
  allIncome: any = [];

  listOfCategory : any[] = [
    "Youtube",
    "GoogleAdsense",
    "Shopify",
    "AmazonFba",
    "Freelancing",
    "Others"
  ]

  constructor(private fb:FormBuilder,
              private nzMessageService:NzMessageService
              ,private router:Router
              ,private incomeService:IncomeService) {
  }
  
  ngOnInit() {
    this.incomeForm = this.fb.group({
      title: [null, [Validators.required]],
      amount : [null, [Validators.required]],
      date: [null, [Validators.required]],
      category : [null, [Validators.required]],
      description: [null, [Validators.required]]
    });
    this.getAllIncome();
  }

  getAllIncome() {
    this.incomeService.getAllIncomeSrv().subscribe(res => {
      this.allIncome = res;
      console.log("-------------------1------------------");
      console.log(this.allIncome);
    }, err => {
      console.log("-------------------3------------------");
      console.log(err);
    });
  }

  postIncome() {
    this.incomeService.postIncomeSrv(this.incomeForm.value).subscribe(res => {
      this.nzMessageService.success('Income Added Successfully', { nzDuration: 2000 });
      this.getAllIncome();
    }, err => {
      this.nzMessageService.error('Error while adding Income', { nzDuration: 2000 });
    });
  }

  updateIncome(id:number) {
    const url = `/income/${id}/edit`;
    this.router.navigateByUrl(url);
  }
  
  deleteIncome(id:number) {
    this.incomeService.DeleteIncomeSrv(id).subscribe(res => {
      this.nzMessageService.success('Income Deleted Successfully', { nzDuration: 2000 });
      this.getAllIncome();
    }, err => {
      this.nzMessageService.error('Error while deleting Income', { nzDuration: 2000 });
    });
  }
}
