import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { BehaviorSubject, tap } from 'rxjs';
import { Dialog1Component } from 'src/app/components/dialog1/dialog1.component';
import { ILocation } from 'src/app/interfaces/location.interface';
import { FlightService } from '../../services/flight.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrls: ['./hotels.component.scss'],
})
export class HotelsComponent implements OnInit {
  myForm!: FormGroup;
  locationsDestinySource = new BehaviorSubject<ILocation[]>([]);
  locationsDestiny$ = this.locationsDestinySource.asObservable();
  constructor(
    private formBuilder: FormBuilder,
    private flightSvc: FlightService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.myForm = this.initForm();
  }

  initForm(): FormGroup {
    return this.formBuilder.group({
      destiny: [
        '',
        [
          Validators.required,
          Validators.pattern('([A-Z a-z-])+'),
          Validators.minLength(3),
          Validators.maxLength(50),
        ],
      ],
    });
  }

  onTypingDestiny() {
    if (this.myForm.get('destiny')?.value.length >= 3) {
      this.flightSvc
        .searchCityAndAirport(this.myForm.get('destiny')?.value, true)
        .pipe(
          tap((res: any) => {
            // console.log(res.data);
            this.locationsDestinySource.next(res.data);
          })
        )
        .subscribe();
    }
  }

  onSubmit() {
    if (this.myForm.invalid) {
      this.myForm.markAllAsTouched();
      this.dialog.open(Dialog1Component, { disableClose: true });
      return;
    }
  }
}
