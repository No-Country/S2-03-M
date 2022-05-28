import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import * as _moment from 'moment';
import { MatDialog } from '@angular/material/dialog';
import { Dialog1Component } from '../../components/dialog1/dialog1.component';
import { FlightService } from '../../services/flight.service';
import { BehaviorSubject, tap } from 'rxjs';

const moment = _moment;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  myForm!: FormGroup;
  radioBtnError: boolean = false;
  locationsSource = new BehaviorSubject<any[]>([]);
  locations$ = this.locationsSource.asObservable();

  constructor(
    private formBuilder: FormBuilder,
    public dialog: MatDialog,
    private flightSvc: FlightService
  ) {
    moment.locale('es');
  }

  ngOnInit(): void {
    this.myForm = this.initForm();
  }

  onTypingFrom() {
    if (this.myForm.get('from')?.value.length >= 3) {
      this.flightSvc
        .searchCityAndAirport(this.myForm.get('from')?.value, false)
        .pipe(
          tap((res: any) => {
            // console.log(res);
            this.locationsSource.next(res.data);
          })
        )
        .subscribe();
    }
  }

  onTypingTo() {
    if (this.myForm.get('to')?.value.length >= 3) {
      this.flightSvc
        .searchCityAndAirport(this.myForm.get('to')?.value, false)
        .pipe(
          tap((res: any) => {
            // console.log(res);
            this.locationsSource.next(res.data);
          })
        )
        .subscribe();
    }
  }

  initForm(): FormGroup {
    return this.formBuilder.group({
      from: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(50),
        ],
      ],
      to: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(50),
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
