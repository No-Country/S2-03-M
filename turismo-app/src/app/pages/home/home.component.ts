import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import * as _moment from 'moment';
import { MatDialog } from '@angular/material/dialog';
import { Dialog1Component } from '../../components/dialog1/dialog1.component';
import { FlightService } from '../../services/flight.service';
import { BehaviorSubject, tap } from 'rxjs';
import { ILocation } from '../../interfaces/location.interface';
import { Router } from '@angular/router';

const moment = _moment;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  myForm!: FormGroup;
  radioBtnError: boolean = false;
  locationsFromSource = new BehaviorSubject<ILocation[]>([]);
  locationsFrom$ = this.locationsFromSource.asObservable();
  locationsToSource = new BehaviorSubject<ILocation[]>([]);
  locationsTo$ = this.locationsToSource.asObservable();

  constructor(
    private formBuilder: FormBuilder,
    public dialog: MatDialog,
    private flightSvc: FlightService,
    private router: Router
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
            // console.log(res.data);
            this.locationsFromSource.next(res.data);
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
            this.locationsToSource.next(res.data);
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
      from: this.myForm.get('from')!.value.slice(-3),
      to: this.myForm.get('to')!.value.slice(-3),
      departure: this.myForm.get('departure')!.value._d,
      return:
        this.myForm.get('trip')!.value === 'round'
          ? this.myForm.get('return')!.value._d
          : '',
      trip: this.myForm.get('trip')!.value,
    };
    console.log('On Submit ->', myForm);

    this.flightSvc.flightQuerySource.next(myForm);
    this.router.navigate(['/flight-offers']);

    /* this.flightSvc.findFlight<Flight[]>(this.origin.iataCode, this.destination.iataCode, this.date, true)
        .pipe(
          debounceTime(2000),
          tap((res: any) => {
            console.log(res);

          }),
        ).subscribe({
          next: (res: any) => {
            this.flights = res.data;
            console.log(this.flights);
            this.departureDateTemplate = false;
            this.flightTemplate = true;
          },
          error: (error: any) => {
            console.log(error);

          }
        }); */
  }
}
