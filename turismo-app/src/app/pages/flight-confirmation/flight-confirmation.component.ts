import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import * as _moment from 'moment';
import { Dialog1Component } from 'src/app/components/dialog1/dialog1.component';
import { FlightQuery } from '../../interfaces/flight-query.interface';

const moment = _moment;

@Component({
  selector: 'app-flight-confirmation',
  templateUrl: './flight-confirmation.component.html',
  styleUrls: ['./flight-confirmation.component.scss'],
})
export class FlightConfirmationComponent implements OnInit {
  myForm!: FormGroup;
  flightQuery!: FlightQuery;
  passengers!: any[];

  constructor(private formBuilder: FormBuilder, private dialog: MatDialog) {
    moment.locale('es');
  }

  ngOnInit(): void {
    this.myForm = this.initForm();
    const flightQuery = localStorage.getItem('flightQuery');
    if (flightQuery !== null) {
      this.flightQuery = JSON.parse(flightQuery);
      console.log(this.flightQuery.passengers);
      this.passengers = new Array(this.flightQuery.passengers);
    }
  }

  initForm(): FormGroup {
    return this.formBuilder.group({
      name: [
        '',
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50),
      ],
      lastname: [
        '',
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50),
      ],
      docnumber: [
        '',
        [
          Validators.required,
          Validators.pattern('[0-9]+'),
          Validators.minLength(8),
          Validators.maxLength(8),
        ],
      ],
      telnumber: [
        '',
        [
          Validators.required,
          // Pattern para números de teléfono:
          Validators.pattern(/((\+[0-9]{1,2}))(\d){11,12}/),
          Validators.maxLength(14),
        ],
      ],
      email: ['', [Validators.required, Validators.email]],
      birthday: [
        this.myForm
          ? moment(this.myForm.get('birthday')!.value).format('YYYY-MM-DD')
          : '',
        [Validators.required],
      ],
      nationality: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.pattern('[a-z A-Z]+'),
        ],
      ],
    });
  }

  onSubmit() {
    if (this.myForm.invalid) {
      this.myForm.markAllAsTouched();
      this.dialog.open(Dialog1Component, { disableClose: true });
      return;
    }

    console.log(this.myForm.value);
  }
}
