import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import * as _moment from 'moment';
import { MatDialog } from '@angular/material/dialog';
import { Dialog1Component } from '../../components/dialog1/dialog1.component';

const moment = _moment;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  myForm!: FormGroup;
  radioBtnError: boolean = false;

  constructor(private formBuilder: FormBuilder, public dialog: MatDialog) {
    moment.locale('es');
  }

  ngOnInit(): void {
    this.myForm = this.initForm();
  }

  initForm(): FormGroup {
    return this.formBuilder.group({
      from: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(15),
        ],
      ],
      to: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(15),
        ],
      ],
      departure: [
        this.myForm ? moment(this.myForm.get('departure')?.value) : '',
        [Validators.required],
      ],
      return: [
        this.myForm ? moment(this.myForm.get('return')?.value) : '',
        [Validators.required],
      ],
      trip: ['', [Validators.required]],
    });
  }

  onClickOneway(): void {
    this.myForm.controls['return'].disable();
  }

  onClickRound(): void {
    this.myForm.controls['return'].enable();
  }

  onSubmit(): void {
    if (this.myForm.invalid) {
      this.myForm.markAllAsTouched();
      this.radioBtnError = true;
      this.dialog.open(Dialog1Component, { disableClose: true });
      return;
    }

    let myForm = {
      from: this.myForm.get('from')!.value,
      to: this.myForm.get('to')!.value,
      departure: this.myForm.get('departure')!.value._d,
      return:
        this.myForm.get('trip')!.value === 'round'
          ? this.myForm.get('return')!.value._d
          : '',
      trip: this.myForm.get('trip')!.value,
    };
    console.log('On Submit ->', myForm);
  }
}
