import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
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
      for (let passenger of this.passengers) {
        this.contactForms.push(
          this.formBuilder.group({
            name: this.formBuilder.control(
              // name
              '',
              [
                Validators.required,
                Validators.minLength(3),
                Validators.maxLength(50),
              ]
            ),
            lastname: this.formBuilder.control(
              // lastname
              '',
              [
                Validators.required,
                Validators.minLength(3),
                Validators.maxLength(50),
              ]
            ),
            docnumber: this.formBuilder.control(
              // docnumber
              '',
              [
                Validators.required,
                Validators.pattern('[0-9]+'),
                Validators.minLength(8),
                Validators.maxLength(8),
              ]
            ),
            telnumber: this.formBuilder.control(
              // telnumber
              '',
              [
                Validators.required,
                // Pattern para números de teléfono:
                Validators.pattern(/((\+[0-9]{1,2}))(\d){11,12}/),
                Validators.maxLength(14),
              ]
            ),
            email: this.formBuilder.control(
              // email
              '',
              [Validators.required, Validators.email]
            ),
            birthday: this.formBuilder.control(
              // birthday
              '',
              [Validators.required]
            ),
            nationality: this.formBuilder.control(
              // nationality
              '',
              [
                Validators.required,
                Validators.minLength(3),
                Validators.pattern('[a-z A-Z]+'),
              ]
            ),
          })
        );
      }
    }
  }

  initForm(): FormGroup {
    return this.formBuilder.group({
      contactForms: this.formBuilder.array([]),
    });
  }

  get contactForms() {
    return this.myForm.get('contactForms') as FormArray;
  }

  onSubmit() {
    if (this.myForm.invalid) {
      this.myForm.markAllAsTouched();
      this.dialog.open(Dialog1Component, { disableClose: true });
      return;
    }

    console.log(this.myForm.value.contactForms);

    let myForm = [];

    for (let contactForm of this.myForm.value.contactForms) {
      const contact = {
        name: contactForm.name,
        lastname: contactForm.lastname,
        docnumber: contactForm.docnumber,
        telnumber: contactForm.telnumber.slice(1),
        email: contactForm.email,
        birthday: moment(contactForm.birthday).format('DD-MM-YYYY'),
        nationality: contactForm.nationality,
      };
      myForm.push(contact);
    }

    console.log(myForm);
  }
}
